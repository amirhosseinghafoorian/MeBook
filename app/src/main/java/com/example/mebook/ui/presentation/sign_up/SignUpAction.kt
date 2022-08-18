package com.example.mebook.ui.presentation.sign_up

sealed interface SignUpAction {
    data class ConfirmSignUp(val username: String, val password: String) : SignUpAction
}