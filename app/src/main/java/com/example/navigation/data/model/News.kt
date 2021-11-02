package com.example.navigation.data.model

data class News(
    val articles: List<Article>?,
    val status: String?,
    val totalResults: Int?
)