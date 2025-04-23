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
    }
}