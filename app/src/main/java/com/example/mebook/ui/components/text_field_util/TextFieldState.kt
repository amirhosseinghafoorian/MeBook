package com.example.mebook.ui.components.text_field_util

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

open class TextFieldState(
    private val validator: (String) -> String? = { null },
) {

    var text by mutableStateOf("")
    var errorMessage by mutableStateOf<String?>(null)

    fun isError() = errorMessage == null

    fun validate() {
        errorMessage = validator(text)
    }

}