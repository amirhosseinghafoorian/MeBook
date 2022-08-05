package com.example.mebook.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mebook.navigation.MeBookScreens.ARTICLE_ROUTE
import com.example.mebook.navigation.MeBookScreens.AUTHENTICATE_ROUTE
import com.example.mebook.navigation.MeBookScreens.HOME_ROUTE
import com.example.mebook.navigation.MeBookScreens.LOGIN_ROUTE
import com.example.mebook.navigation.MeBookScreens.PROFILE_ROUTE
import com.example.mebook.navigation.MeBookScreens.SEARCH_ROUTE
import com.example.mebook.navigation.MeBookScreens.SIGN_UP_ROUTE
import com.example.mebook.navigation.MeBookScreens.SPLASH_ROUTE
import com.example.mebook.navigation.MeBookScreens.WRITE_ROUTE
import com.example.mebook.ui.presentation.article.ArticleScreen
import com.example.mebook.ui.presentation.authenticate.AuthenticateScreen
import com.example.mebook.ui.presentation.home.HomeScreen
import com.example.mebook.ui.presentation.login.LoginScreen
import com.example.mebook.ui.presentation.profile.ProfileScreen
import com.example.mebook.ui.presentation.search.SearchScreen
import com.example.mebook.ui.presentation.sign_up.SignUpScreen
import com.example.mebook.ui.presentation.splash.SplashScreen
import com.example.mebook.ui.presentation.write.WriteScreen

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
        composable(route = SPLASH_ROUTE) {
            SplashScreen(navController)
        }
        composable(route = AUTHENTICATE_ROUTE) {
            AuthenticateScreen(navController)
        }
        composable(route = LOGIN_ROUTE) {
            LoginScreen(navController)
        }
        composable(route = SIGN_UP_ROUTE) {
            SignUpScreen(navController)
        }
        composable(route = HOME_ROUTE) {
            HomeScreen(navController)
        }
        composable(route = PROFILE_ROUTE) {
            ProfileScreen(navController)
        }
        composable(route = SEARCH_ROUTE) {
            SearchScreen(navController)
        }
        composable(route = WRITE_ROUTE) {
            WriteScreen(navController)
        }
        composable(route = ARTICLE_ROUTE) {
            ArticleScreen(navController)
        }
    }
}