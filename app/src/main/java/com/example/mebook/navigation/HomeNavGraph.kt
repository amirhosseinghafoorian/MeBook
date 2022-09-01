package com.example.mebook.navigation

import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mebook.navigation.MeBookScreens.HomeNavRoute
import com.example.mebook.navigation.MeBookScreens.HomeRoute
import com.example.mebook.navigation.MeBookScreens.SearchRoute
import com.example.mebook.ui.components.MeBookScaffold
import com.example.mebook.ui.presentation.home.HomeScreen
import com.example.mebook.ui.presentation.search.SearchScreen

fun NavGraphBuilder.homeNavGraph(navController: NavController) {
    composable(route = HomeNavRoute.route) {
        HomeHost(mainController = navController)
    }
}

@Composable
fun HomeHost(
    modifier: Modifier = Modifier,
    mainController: NavController,
    navController: NavHostController = rememberNavController()
) {
    val scaffoldState = rememberScaffoldState()

    MeBookScaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
            BottomNavigation(navController, mainController)
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = HomeRoute.route,
            modifier = modifier
        ) {
            composable(route = HomeRoute.route) {
                HomeScreen(mainController, scaffoldState)
            }
            composable(route = SearchRoute.route) {
                SearchScreen(mainController)
            }
        }
    }
}