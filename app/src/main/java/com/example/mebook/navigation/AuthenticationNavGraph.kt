package com.example.mebook.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.mebook.navigation.MeBookScreens.AuthenticateRoute
import com.example.mebook.navigation.MeBookScreens.AuthNavRoute
import com.example.mebook.navigation.MeBookScreens.LoginRoute
import com.example.mebook.navigation.MeBookScreens.SignUpRoute
import com.example.mebook.ui.presentation.authenticate.AuthenticateScreen
import com.example.mebook.ui.presentation.login.LoginScreen
import com.example.mebook.ui.presentation.sign_up.SignUpScreen

fun NavGraphBuilder.authenticateNavGraph(navController: NavController) {
    navigation(
        startDestination = AuthenticateRoute.route,
        route = AuthNavRoute.route
    ) {
        composable(route = AuthenticateRoute.route) {
            AuthenticateScreen(navController)
        }
        composable(route = LoginRoute.route) {
            LoginScreen(navController)
        }
        composable(route = SignUpRoute.route) {
            SignUpScreen(navController)
        }
    }
}