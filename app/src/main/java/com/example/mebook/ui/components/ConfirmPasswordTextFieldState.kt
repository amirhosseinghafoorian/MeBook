package com.example.mebook.ui.components

import com.example.mebook.ui.components.text_field_util.TextFieldState

class ConfirmPasswordTextFieldState : TextFieldState() {

    override fun validate(): Boolean {
        errorMessage = if (text.isEmpty()) "username cannot be empty"
        else null
        return isValid()
    }

    fun secondaryValidate(password : String) : Boolean{
        errorMessage = if (text != password) "confirm password different"
        else null
        return isValid()
    }

}