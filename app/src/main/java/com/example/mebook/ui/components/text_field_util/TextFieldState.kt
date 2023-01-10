package com.example.mebook.ui.components.text_field_util

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

abstract class TextFieldState {
    // todo viewModels should have a map of TextFieldStates
    //  and by calling that manager all textFieldStates would be validated together

    var text by mutableStateOf("")
    var errorMessage by mutableStateOf<String?>(null)

    private val limitersList = mutableListOf<ConditionLimiter>()

    abstract fun validate(): Boolean

    fun isError() = errorMessage != null
    fun isValid() = errorMessage == null

    fun onChanged(newText: String) {
        if (
            newText.isEmpty()
            || limitersList.all { limiter -> limiter.isValid(newText) }
        ) {
            text = newText
            errorMessage = null
        }
    }

    fun addLimiter(limiter: ConditionLimiter) {
        limitersList.add(limiter)
    }

}