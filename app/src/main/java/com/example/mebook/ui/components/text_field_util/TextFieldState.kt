package com.example.mebook.ui.components.text_field_util

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

abstract class TextFieldState {

    // todo implementations should get a 'validators' property
    //  which is a list of all validation conditions textField need

    // todo should get onChanged callback which both sets the text and resets the validation state

    // todo viewModels should have a map of TextFieldStates
    //  and by calling that manager all textFieldStates would be validated together

    var text by mutableStateOf("")
    var errorMessage by mutableStateOf<String?>(null)

    fun isError() = errorMessage != null
    fun isValid() = errorMessage == null

    abstract fun validate(): Boolean

}