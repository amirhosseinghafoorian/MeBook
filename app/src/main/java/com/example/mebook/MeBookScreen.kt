package com.example.mebook

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import com.example.mebook.navigation.MeBookNavGraph
import com.example.mebook.ui.theme.MeBookTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun MeBookScreen() {
    MeBookTheme {

        val systemUiController = rememberSystemUiController()
        val backgroundColor = MaterialTheme.colors.background
        val isLight = MaterialTheme.colors.isLight
        SideEffect {
            systemUiController.setSystemBarsColor(backgroundColor, isLight)
//          todo can be used to remove navigation bar
//           systemUiController.isNavigationBarVisible = false
        }

        MeBookNavGraph()
    }
}