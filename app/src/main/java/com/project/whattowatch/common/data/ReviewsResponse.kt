package com.project.whattowatch.common.data

import com.google.gson.annotations.SerializedName

data class ReviewsResponse(
    @SerializedName("results") val movies: List<ReviewsModel>,
)