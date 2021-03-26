package com.project.whattowatch.common.data

import com.google.gson.annotations.SerializedName

data class ReviewsModel(
    @SerializedName("author") val author: String,
    @SerializedName("content") val content: String
)