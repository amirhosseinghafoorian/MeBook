package com.example.mebook.ui.components.text_field_util

class BasicTextFieldState : TextFieldState() {

    override fun validate(): Boolean {
        return isValid()
    }

}