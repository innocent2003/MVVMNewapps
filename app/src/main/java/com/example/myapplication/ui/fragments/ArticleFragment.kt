package com.example.myapplication.ui.fragments

import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.models.Article
import com.example.myapplication.ui.NewsActivity
import com.example.myapplication.ui.NewsViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class ArticleFragment : Fragment(R.layout.fragment_article) {

    lateinit var viewModel: NewsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel

        val article = arguments?.getSerializable("article") as? Article
            ?: return

        val webView = view.findViewById<WebView>(R.id.webView)
        val fab = view.findViewById<FloatingActionButton>(R.id.fab)
        val articleUrl = article.url ?: return

        webView.webViewClient = WebViewClient()
        webView.loadUrl(articleUrl)

        fab.setOnClickListener {
            viewModel.saveArticle(article)
            Snackbar.make(view, "Article saved successfully", Snackbar.LENGTH_SHORT).show()
        }
    }
}