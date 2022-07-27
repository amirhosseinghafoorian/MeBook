package com.example.mebook.feature.presentation.authenticate

sealed class AuthenticateAction {
    data class SnackBar(val message: String) : AuthenticateAction()
    data class ChangeName(val name: String) : AuthenticateAction()
    data class Navigate(val route: String) : AuthenticateAction()
    object NavigateUp : AuthenticateAction()
}
