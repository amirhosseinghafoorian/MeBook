package com.example.mebook.navigation

import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mebook.navigation.MeBookScreens.HOME_NAV_ROUTE
import com.example.mebook.navigation.MeBookScreens.HOME_ROUTE
import com.example.mebook.navigation.MeBookScreens.SEARCH_ROUTE
import com.example.mebook.ui.presentation.home.HomeScreen
import com.example.mebook.ui.presentation.search.SearchScreen

fun NavGraphBuilder.homeNavGraph(navController: NavController) {
    composable(route = HOME_NAV_ROUTE) {
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
    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
            BottomNavigation(navController, mainController)
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = HOME_ROUTE,
            modifier = modifier
        ) {
            composable(route = HOME_ROUTE) {
                HomeScreen(mainController, scaffoldState)
            }
            composable(route = SEARCH_ROUTE) {
                SearchScreen(mainController)
            }
        }
    }
}