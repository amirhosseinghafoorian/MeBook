package com.example.mebook.ui.components.text_field_util

class ConditionValidator(
    val errorMassage: String,
    private val condition: (String) -> Boolean
) {

    fun validate(text: String): Boolean {
        return condition(text)
    }

}