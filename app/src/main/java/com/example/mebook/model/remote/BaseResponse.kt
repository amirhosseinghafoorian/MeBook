package com.example.mebook.model.remote

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    val resultCode: Int,
    @SerializedName("message") val resultMessage: String,
    val body: T
)
