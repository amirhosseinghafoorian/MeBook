package com.example.mebook.ui.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.mebook.navigation.MeBookScreens

@Composable
fun HomeScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "welcome to home")
        Button(onClick = {
            navController.navigate(MeBookScreens.AUTH_NAV_ROUTE)
        }) {
            Text(text = "auth")
        }
        Button(onClick = {
            navController.navigate(MeBookScreens.WRITE_ROUTE)
        }) {
            Text(text = "write")
        }
    }
}