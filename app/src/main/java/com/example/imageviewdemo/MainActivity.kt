package com.example.imageviewdemo

import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.Bitmap.createBitmap
import android.graphics.Canvas
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
//    private lateinit var recyclerView: RecyclerView
//    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        webView = findViewById<WebView>(R.id.web_view)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, PreviewWindow())
            commit()
        }

//        val data = mutableListOf<Bitmap>()
//        val adapter = PreviewCardAdapter(data)
//
//        recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
//
//        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//        } else {
//            recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
//        }
//        recyclerView.adapter = adapter
//
//        val urlList = listOf("https://www.google.com", "https://www.youtube.com", "https://www.gmail.com")
//        startWebViewCapture(webView, urlList) { bitmap ->
//            data.add(bitmap)
//            adapter.notifyItemInserted(data.lastIndex)
//            Log.i("PreviewCardAdapter", "width: ${bitmap.width}, height: ${bitmap.height}")
//        }
    }

//    private fun startWebViewCapture(webView: WebView, urlList: List<String>, onBitmapCaptured: (Bitmap) -> Unit) {
//        var index = 0
//
//        webView.webViewClient = object : WebViewClient() {
//            override fun onPageFinished(view: WebView?, url: String?) {
//                super.onPageFinished(view, url)
//                webView.postDelayed({
//                    Log.i(TAG, "index is ${index}")
//                    val bitmap = captureWebView(webView)
//                    onBitmapCaptured(bitmap)
//
//                    index++
//
//                    if (index < urlList.size) {
//                        webView.loadUrl(urlList[index])
//                    }
//                }, 1000)
//            }
//        }
//        webView.loadUrl(urlList[index])
//    }
//
//    private fun captureWebView(webView: WebView): Bitmap {
//        webView.visibility = View.INVISIBLE
//        val bitmap = createBitmap(webView.width, webView.height, Bitmap.Config.ARGB_8888)
//        val canvas = Canvas(bitmap)
//
//        webView.draw(canvas)
//        webView.visibility = View.GONE
//        return bitmap
//    }
}