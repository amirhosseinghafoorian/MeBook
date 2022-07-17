package com.example.mebook.feature.presentation.sign_up

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
fun SignUpScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Sign Up")

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            navController.navigate(LOGIN_ROUTE) {
                popUpTo(SIGN_UP_ROUTE) {
                    inclusive = true
                }
            }
        }) {
            Text("wanna login ?")
        }
    }

}