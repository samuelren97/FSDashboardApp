package com.shunta.fsdashboard.composables.generics

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebView(
    url: String,
    height: Int = ViewGroup.LayoutParams.MATCH_PARENT,
    modifier: Modifier = Modifier
) {
    Column( modifier = modifier.fillMaxWidth()) {
        AndroidView (
            factory = { context ->
                WebView(context).apply {
                    webViewClient = WebViewClient()
                    settings.javaScriptEnabled = true
                    loadUrl(url)
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        height
                    )
                }
            },
            update = { webView ->
                webView.loadUrl(url)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WebViewPreview() {
    WebView(
        url = "https://google.ca/",
        height = 300
    )
}