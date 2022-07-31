package com.example.mebook.ui.presentation.authenticate

sealed interface AuthenticateAction {
    object CallApi : AuthenticateAction
    object CallDatabase : AuthenticateAction
    data class Navigate(val route: String) : AuthenticateAction
    object NavigateUp : AuthenticateAction
}
