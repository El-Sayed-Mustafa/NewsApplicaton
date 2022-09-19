package com.androiddevs.mvvmnewsapp.UI.fragments

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.androiddevs.mvvmnewsapp.R
import com.androiddevs.mvvmnewsapp.UI.NewsActivity
import com.androiddevs.mvvmnewsapp.UI.NewsViewModel
import kotlinx.android.synthetic.main.fragment_article.*

class ArticalFragment :Fragment(R.layout.fragment_article){

    lateinit var viewModel:NewsViewModel

    val args:ArticalFragmentArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel

        val article = args.article

        webView.apply {

            webViewClient = WebViewClient()
            loadUrl(article.url)
        }
    }
}