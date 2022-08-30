package com.example.mebook.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mebook.navigation.MeBookScreens.ArticleRoute
import com.example.mebook.navigation.MeBookScreens.FullArticlesRoute
import com.example.mebook.navigation.MeBookScreens.ProfileRoute
import com.example.mebook.navigation.MeBookScreens.SplashRoute
import com.example.mebook.navigation.MeBookScreens.WriteRoute
import com.example.mebook.ui.presentation.article.ArticleScreen
import com.example.mebook.ui.presentation.full_articles.FullArticlesScreen
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
        startDestination = SplashRoute.route,
        modifier = modifier
    ) {
        composable(route = SplashRoute.route) {
            SplashScreen(navController)
        }
        composable(
            route = ProfileRoute.route,
            arguments = listOf(
                navArgument("username") {
                    nullable = true
                    type = NavType.StringType
                }
            )
        ) {
            ProfileScreen(navController)
        }
        composable(route = WriteRoute.route) {
            WriteScreen(navController)
        }
        composable(route = ArticleRoute.route) {
            ArticleScreen(navController)
        }
        composable(
            route = FullArticlesRoute.route,
            arguments = listOf(
                navArgument("type") {
                    nullable = false
                    type = NavType.StringType
                }
            )
        ) {
            FullArticlesScreen(navController)
        }
        homeNavGraph(navController)
        authenticateNavGraph(navController)
    }
}