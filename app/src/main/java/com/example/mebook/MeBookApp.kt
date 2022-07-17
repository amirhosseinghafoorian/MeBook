package com.example.mebook

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.mebook.ui.theme.MeBookTheme

@Composable
fun MeBookApp() {
    MeBookTheme {
        val navController = rememberNavController()
        MeBookNavGraph(navController = navController)
    }
}