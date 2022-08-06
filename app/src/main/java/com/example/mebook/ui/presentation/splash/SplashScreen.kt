package com.example.mebook.ui.presentation.splash

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.mebook.navigation.MeBookScreens.AUTHENTICATE_ROUTE
import com.example.mebook.navigation.MeBookScreens.HOME_NAV_ROUTE
import com.example.mebook.navigation.MeBookScreens.SPLASH_ROUTE
import com.example.mebook.ui.components.MeBookScaffold

@Composable
fun SplashScreen(navController: NavController) {
    MeBookScaffold {
        Column {
            Button(onClick = {
                navController.navigate(HOME_NAV_ROUTE) {
                    popUpTo(SPLASH_ROUTE) {
                        inclusive = true
                    }
                }
            }) {
                Text(text = "home")
            }

            Button(onClick = {
                navController.navigate(AUTHENTICATE_ROUTE) {
                    popUpTo(SPLASH_ROUTE) {
                        inclusive = true
                    }
                }
            }) {
                Text(text = "auth")
            }
        }
    }
}