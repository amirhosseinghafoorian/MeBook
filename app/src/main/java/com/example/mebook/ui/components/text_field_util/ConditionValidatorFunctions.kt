package com.example.mebook.ui.components.text_field_util

object ConditionValidatorFunctions {

    val text_notBlank = { text: String ->
        text.isNotBlank()
    }

    val text_lengthMoreThan4 = { text: String ->
        text.length > 4
    }

    val text_lengthLessThan20 = { text: String ->
        text.length < 20
    }
}