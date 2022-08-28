package com.example.mebook.ui.presentation.full_articles

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun FullArticlesScreen(
    navController: NavController
) {
    FullArticlesScreen(
        navController = navController,
        viewModel = hiltViewModel()
    )
}

@Composable
fun FullArticlesScreen(
    navController: NavController,
    viewModel: FullArticlesViewModel
) {

}