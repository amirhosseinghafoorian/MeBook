package com.example.mebook.ui.components.text_field_util

class BasicTextFieldState : TextFieldState(
    validator = ::isTextValid
)

private fun isTextValid(text: String): String? {
    return if (text.isEmpty()) "username cannot be empty"
    else null
}