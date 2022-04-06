package ru.gozerov.data.remote.news.models

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("status")
    val status: String,

    @SerializedName("totalResults")
    val totalArticles: Int,

    @SerializedName("articles")
    val articles: List<NewsApi>
)
