package com.example.mebook.ui.presentation.splash

import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.mebook.navigation.MeBookScreens

@Composable
fun SplashScreen(
    navController: NavController
) {
    Scaffold(
        scaffoldState = rememberScaffoldState()
    ) {
        Button(onClick = {
            navController.navigate(MeBookScreens.HOME_ROUTE)
        }) {
            Text(text = "home")
        }
    }
}