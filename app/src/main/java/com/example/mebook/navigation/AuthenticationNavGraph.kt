package com.example.mebook.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.mebook.navigation.MeBookScreens.AUTHENTICATE_ROUTE
import com.example.mebook.navigation.MeBookScreens.AUTH_NAV_ROUTE
import com.example.mebook.navigation.MeBookScreens.LOGIN_ROUTE
import com.example.mebook.navigation.MeBookScreens.SIGN_UP_ROUTE
import com.example.mebook.ui.presentation.authenticate.AuthenticateScreen
import com.example.mebook.ui.presentation.login.LoginScreen
import com.example.mebook.ui.presentation.sign_up.SignUpScreen

fun NavGraphBuilder.authenticateNavGraph(navController: NavController) {
    navigation(
        startDestination = AUTHENTICATE_ROUTE,
        route = AUTH_NAV_ROUTE
    ) {
        composable(route = AUTHENTICATE_ROUTE) {
            AuthenticateScreen(navController)
        }
        composable(route = LOGIN_ROUTE) {
            LoginScreen(navController)
        }
        composable(route = SIGN_UP_ROUTE) {
            SignUpScreen(navController)
        }
    }
}