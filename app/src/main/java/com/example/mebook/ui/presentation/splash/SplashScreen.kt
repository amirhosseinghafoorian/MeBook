package com.example.mebook.ui.presentation.splash

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mebook.navigation.MeBookScreens.AUTHENTICATE_ROUTE
import com.example.mebook.navigation.MeBookScreens.HOME_NAV_ROUTE
import com.example.mebook.navigation.MeBookScreens.SPLASH_ROUTE
import com.example.mebook.ui.components.MeBookButton
import com.example.mebook.ui.components.MeBookScaffold

@Composable
fun SplashScreen(navController: NavController) {
    SplashScreen(
        navController = navController,
        viewModel = hiltViewModel()
    )
}

@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: SplashViewModel
) {

    val uiState by viewModel.uiState.collectAsState()

    SplashScreen {
        if (uiState.isLoggedIn == true) {
            navController.navigate(HOME_NAV_ROUTE) {
                popUpTo(SPLASH_ROUTE) {
                    inclusive = true
                }
            }
        } else if (uiState.isLoggedIn == false) {
            navController.navigate(AUTHENTICATE_ROUTE) {
                popUpTo(SPLASH_ROUTE) {
                    inclusive = true
                }
            }
        }
    }
}

@Composable
fun SplashScreen(
    loggedInResult: () -> Unit
) {
    MeBookScaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            MeBookButton(onClick = { loggedInResult() }) {
                Text("check")
            }

        }
    }
}