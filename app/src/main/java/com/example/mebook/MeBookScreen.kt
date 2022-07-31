package com.example.mebook

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.example.mebook.navigation.MeBookNavGraph
import com.example.mebook.ui.theme.MeBookTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun MeBookScreen() {
    MeBookTheme {
        val systemUiController = rememberSystemUiController()
        SideEffect {
            systemUiController.setSystemBarsColor(Color.Transparent, true)
        }

        val navController = rememberNavController()
        MeBookNavGraph(navController = navController)
    }
}