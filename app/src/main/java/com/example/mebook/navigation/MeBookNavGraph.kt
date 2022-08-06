package com.example.mebook.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mebook.navigation.MeBookScreens.ARTICLE_ROUTE
import com.example.mebook.navigation.MeBookScreens.PROFILE_ROUTE
import com.example.mebook.navigation.MeBookScreens.SPLASH_ROUTE
import com.example.mebook.navigation.MeBookScreens.WRITE_ROUTE
import com.example.mebook.ui.presentation.article.ArticleScreen
import com.example.mebook.ui.presentation.profile.ProfileScreen
import com.example.mebook.ui.presentation.splash.SplashScreen
import com.example.mebook.ui.presentation.write.WriteScreen

@Composable
fun MeBookNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = SPLASH_ROUTE,
        modifier = modifier
    ) {
        composable(route = SPLASH_ROUTE) {
            SplashScreen(navController)
        }
        composable(route = PROFILE_ROUTE) {
            ProfileScreen(navController)
        }
        composable(route = WRITE_ROUTE) {
            WriteScreen(navController)
        }
        composable(route = ARTICLE_ROUTE) {
            ArticleScreen(navController)
        }
        homeNavGraph(navController)
        authenticateNavGraph(navController)
    }
}