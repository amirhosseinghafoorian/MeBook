package com.example.mebook.ui.presentation.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mebook.navigation.MeBookScreens.AuthenticateRoute
import com.example.mebook.navigation.MeBookScreens.HomeNavRoute
import com.example.mebook.navigation.MeBookScreens.SplashRoute
import com.example.mebook.ui.components.MeBookScaffold
import com.example.mebook.ui.components.rememberAnimationState

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

    val animationState = rememberAnimationState()

    LaunchedEffect(animationState.isIdle) {
        if (animationState.isIdle) {
            if (uiState.isLoggedIn == true) {
                navController.navigate(HomeNavRoute.route) {
                    popUpTo(SplashRoute.route) {
                        inclusive = true
                    }
                }
            } else if (uiState.isLoggedIn == false) {
                navController.navigate(AuthenticateRoute.route) {
                    popUpTo(SplashRoute.route) {
                        inclusive = true
                    }
                }
            }
        }
    }

    MeBookScaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 64.dp, horizontal = 32.dp)
                .clip(MaterialTheme.shapes.medium)
                .background(MaterialTheme.colors.primary),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            AnimatedVisibility(animationState, enter = fadeIn(tween(2000))) {
                Text(
                    text = "MeBook",
                    style = MaterialTheme.typography.h2.copy(
                        color = MaterialTheme.colors.surface,
                        fontFamily = FontFamily.Cursive
                    )
                )
            }
        }
    }
}