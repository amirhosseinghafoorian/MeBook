package com.example.mebook.data.util

import com.example.mebook.model.remote.BaseResponse

fun <T> BaseResponse<T>.getOrThrow(): T {
    if (resultCode == 200) {
        return body
    } else {
        throw Exception(resultMessage)
    }
}