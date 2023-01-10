package com.example.mebook.ui.components.text_field_util


import com.example.mebook.ui.components.text_field_util.ConditionValidatorFunctions.text_lengthLessThan20
import com.example.mebook.ui.components.text_field_util.ConditionValidatorFunctions.text_lengthMoreThan4
import com.example.mebook.ui.components.text_field_util.ConditionValidatorFunctions.text_notBlank
import com.example.mebook.ui.util.firstONull

class UsernameTextFieldState : TextFieldState() {

    // todo should update all TextFieldStates like this

    private val validatorsList = listOf(
        ConditionValidator(
            errorMassage = "username cannot be empty",
            condition = text_notBlank
        ),
        ConditionValidator(
            errorMassage = "username should be more than 4 characters",
            condition = text_lengthMoreThan4
        ),
        ConditionValidator(
            errorMassage = "username should be less than 20 characters",
            condition = text_lengthLessThan20
        ),
    )

    override fun validate(): Boolean {
        errorMessage = validatorsList.firstONull { validator ->
            !validator.validate(text)
        }?.errorMassage

        return isValid()
    }

}