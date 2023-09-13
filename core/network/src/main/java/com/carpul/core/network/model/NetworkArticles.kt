package com.carpul.core.network.model

import com.google.gson.annotations.SerializedName

data class NetworkArticles(
    @SerializedName("articles")
    val articleResponse: List<NetworkNewsResource>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
)