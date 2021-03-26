package com.project.whattowatch.common.data

import com.google.gson.annotations.SerializedName

open class ErrorResponse(
    @SerializedName("error")
    val error: Error,
    @SerializedName("status")
    val status: Int,
    @SerializedName("message")
    val message: String
)