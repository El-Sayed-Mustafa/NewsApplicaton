package com.androiddevs.mvvmnewsapp

data class newsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)