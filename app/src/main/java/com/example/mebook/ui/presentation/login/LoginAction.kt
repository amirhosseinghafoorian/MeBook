package com.example.mebook.ui.presentation.login

sealed interface LoginAction {
    data class ConfirmLogin(val username: String, val password: String) : LoginAction
}