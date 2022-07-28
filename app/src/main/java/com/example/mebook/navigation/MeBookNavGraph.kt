package com.example.mebook.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mebook.navigation.MeBookScreens.AUTHENTICATE_ROUTE
import com.example.mebook.navigation.MeBookScreens.LOGIN_ROUTE
import com.example.mebook.navigation.MeBookScreens.SIGN_UP_ROUTE
import com.example.mebook.ui.presentation.login.LoginScreen
import com.example.mebook.ui.presentation.sign_up.SignUpScreen
import com.example.mebook.ui.presentation.authenticate.AuthenticateScreen

@Composable
fun MeBookNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = AUTHENTICATE_ROUTE,
        modifier = modifier
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