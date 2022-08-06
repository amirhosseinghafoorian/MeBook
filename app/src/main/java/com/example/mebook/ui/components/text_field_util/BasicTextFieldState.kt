package com.example.mebook.ui.components.text_field_util

class BasicTextFieldState : TextFieldState() {

    override fun validate(): Boolean {
        errorMessage = if (text.isEmpty()) "username cannot be empty"
        else null
        return isValid()
    }

}