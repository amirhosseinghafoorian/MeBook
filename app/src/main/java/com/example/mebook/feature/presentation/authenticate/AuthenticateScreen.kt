package com.example.mebook.feature.presentation.authenticate

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mebook.MeBookScreens.LOGIN_ROUTE
import com.example.mebook.MeBookScreens.SIGN_UP_ROUTE

@Composable
fun AuthenticateScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Authenticate Screen")

        Spacer(modifier = Modifier.height(64.dp))

        Button(onClick = {
            navController.navigate(SIGN_UP_ROUTE)
        }) {
            Text("Sign Up")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            navController.navigate(LOGIN_ROUTE)
        }) {
            Text("Login")
        }
    }

}