package com.example.mebook.ui.presentation.splash

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mebook.navigation.MeBookScreens.AUTHENTICATE_ROUTE
import com.example.mebook.navigation.MeBookScreens.HOME_NAV_ROUTE
import com.example.mebook.navigation.MeBookScreens.SPLASH_ROUTE
import com.example.mebook.ui.components.MeBookButton
import com.example.mebook.ui.components.MeBookScaffold

@Composable
fun SplashScreen(navController: NavController) {
    MeBookScaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            MeBookButton(
                onClick = {
                    navController.navigate(HOME_NAV_ROUTE) {
                        popUpTo(SPLASH_ROUTE) {
                            inclusive = true
                        }
                    }
                }
            ) {
                Text(text = "home")
            }

            Spacer(modifier = Modifier.height(16.dp))

            MeBookButton(
                onClick = {
                    navController.navigate(AUTHENTICATE_ROUTE) {
                        popUpTo(SPLASH_ROUTE) {
                            inclusive = true
                        }
                    }
                }
            ) {
                Text(text = "auth")
            }
        }
    }
}