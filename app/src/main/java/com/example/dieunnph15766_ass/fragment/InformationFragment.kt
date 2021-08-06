package com.example.dieunnph15766_ass.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.dieunnph15766_ass.R
import java.io.File
import java.nio.file.FileSystems

class InformationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_information_, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mWebView = view.findViewById<WebView>(R.id.webview_information)
        val webView = WebView(requireActivity().applicationContext)
        val webViewClient = WebViewClient()

        mWebView.webViewClient = webViewClient
        mWebView.loadUrl("file:///android_asset/index.html")
    }
}