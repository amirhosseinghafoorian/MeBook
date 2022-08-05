package com.example.mebook.ui.components.text_field_util

class PasswordTextFieldState : TextFieldState(
    validator = ::isPasswordValid
)

private fun isPasswordValid(text: String): String? {
    return if (text.isEmpty()) "password cannot be empty"
    else if (text.length < 3) "password cannot should be greater than 3 characters"
    else if (text.length > 20) "password cannot should be smaller than 20 characters"
    else null
}