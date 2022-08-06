package com.example.mebook.ui.components.text_field_util

class UsernameTextFieldState : TextFieldState(
    validator = ::isUsernameValid
)

private fun isUsernameValid(text: String): String? {
    return if (text.isEmpty()) "username cannot be empty"
    else if (text.length < 4) "username should be at least 5 characters"
    else if (text.length > 19) "username should be smaller than 20 characters"
    else null
}