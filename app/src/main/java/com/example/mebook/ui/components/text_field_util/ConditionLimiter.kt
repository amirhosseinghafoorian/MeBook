package com.example.mebook.ui.components.text_field_util

class ConditionLimiter(
    private val condition: (String) -> Boolean
) {

    fun isValid(text: String): Boolean {
        return condition(text)
    }

}