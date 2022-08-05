package com.example.mebook.ui.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.mebook.navigation.BottomNavigation

@Composable
fun HomeScreen(
    navController: NavController,
    scaffoldState : ScaffoldState
) {
    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
            BottomNavigation(navController)
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "welcome to home")
        }
    }

}