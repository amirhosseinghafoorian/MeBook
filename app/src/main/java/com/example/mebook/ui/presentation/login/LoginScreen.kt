package com.example.mebook.ui.presentation.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mebook.navigation.MeBookScreens.LOGIN_ROUTE
import com.example.mebook.navigation.MeBookScreens.SIGN_UP_ROUTE

@Composable
fun LoginScreen(
    navController: NavController
) {
    Scaffold(
        scaffoldState = rememberScaffoldState()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Login")

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                navController.navigate(SIGN_UP_ROUTE) {
                    popUpTo(LOGIN_ROUTE) {
                        inclusive = true
                    }
                }
            }) {
                Text("wanna sign in ?")
            }
        }
    }

}