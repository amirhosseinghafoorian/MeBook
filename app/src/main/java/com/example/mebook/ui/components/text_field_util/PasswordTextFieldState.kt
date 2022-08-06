package com.example.mebook.ui.components.text_field_util

class PasswordTextFieldState : TextFieldState() {

    override fun validate(): Boolean {
        errorMessage = if (text.isEmpty()) "password cannot be empty"
        else if (text.length < 7) "password should be at least 8 characters"
        else if (text.length > 19) "password should be smaller than 20 characters"
        else null
        return isValid()
    }

}