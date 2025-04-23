package com.example.imageviewdemo

import android.content.Context
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.Bitmap.createBitmap
import android.graphics.Canvas
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PreviewWindow() : Fragment(R.layout.preview_window) {
    private lateinit var recyclerView: RecyclerView
    private lateinit var webView: WebView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = mutableListOf<Bitmap>()
        val adapter = PreviewCardAdapter(data)

        webView = view.findViewById<WebView>(R.id.web_view)
        recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)

        val displayMetrics = requireContext().resources.displayMetrics
        val screenWidth = displayMetrics.widthPixels
        val screenHeight = displayMetrics.heightPixels

        val aspectRatio = "${if ( screenWidth > screenHeight ) "W" else "H"},$screenWidth:$screenHeight"

        val layoutParams = webView.layoutParams as ConstraintLayout.LayoutParams
        layoutParams.dimensionRatio = aspectRatio

        webView.layoutParams = layoutParams

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        } else {
            recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
        recyclerView.adapter = adapter

        val urlList = listOf("https://www.google.com", "https://www.youtube.com", "https://www.gmail.com")
        viewLifecycleOwner.lifecycleScope.launch {
            startWebViewCapture(webView, urlList, requireContext()) { bitmap ->
                data.add(bitmap)
                adapter.notifyItemInserted(data.lastIndex)
                Log.i("PreviewCardAdapter", "width, height: ${bitmap.width}, ${bitmap.height}")
            }

        }

    }

    private fun startWebViewCapture(webView: WebView, urlList: List<String>, context: Context, onBitmapCaptured: (Bitmap) -> Unit) {
        var index = 0

        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)

                webView.postDelayed({
                    Log.i(TAG, "index is ${index}")
                    val bitmap = captureWebView(webView, context)
                    onBitmapCaptured(bitmap)

                    index++

                    if (index < urlList.size) {
                        webView.loadUrl(urlList[index])
                    }
                }, 1000)
            }
        }
        webView.loadUrl(urlList[index])
    }

    private fun captureWebView(webView: WebView, context: Context): Bitmap {
        webView.visibility = View.INVISIBLE

//        val width = context.resources.displayMetrics.widthPixels
//        val height = context.resources.displayMetrics.heightPixels
        val bitmap = createBitmap(webView.width, webView.height, Bitmap.Config.ARGB_8888)
        Log.i("PreviewCardAdapter", "width, height in captureWebView: ${bitmap.width}, ${bitmap.height}")
        val canvas = Canvas(bitmap)

        webView.draw(canvas)
        webView.visibility = View.GONE
        return bitmap
    }
}