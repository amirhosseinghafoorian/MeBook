package com.example.mebook.ui.presentation.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mebook.navigation.MeBookScreens.AUTH_NAV_ROUTE
import com.example.mebook.navigation.MeBookScreens.HOME_NAV_ROUTE
import com.example.mebook.navigation.MeBookScreens.LOGIN_ROUTE
import com.example.mebook.navigation.MeBookScreens.SIGN_UP_ROUTE
import com.example.mebook.ui.components.MeBookScaffold
import com.example.mebook.ui.components.text_field_util.PasswordTextFieldState
import com.example.mebook.ui.components.text_field_util.UsernameTextFieldState

@Composable
fun LoginScreen(
    navController: NavController
) {
    // todo uiState should be added
    // todo actions should be added
    // todo text fields enabled should depend on uiState loading
    // todo button visibility should depend on uiState loading

    val usernameState = remember { UsernameTextFieldState() }
    val passwordState = remember { PasswordTextFieldState() }

    MeBookScaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = usernameState.text,
                onValueChange = {
                    usernameState.text = it
                },
                placeholder = {
                    Text("Username")
                },
                isError = usernameState.isError()
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = passwordState.text,
                onValueChange = {
                    passwordState.text = it
                },
                placeholder = {
                    Text("Password")
                },
                keyboardOptions = KeyboardOptions.Default
                    .copy(keyboardType = KeyboardType.Password),
                isError = passwordState.isError()
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    if (
                        usernameState.validate() &&
                        passwordState.validate()
                    ) {
                        // todo login api call
                        navController.navigate(HOME_NAV_ROUTE) {
                            popUpTo(AUTH_NAV_ROUTE) {
                                inclusive = true
                            }
                        }
                    }
                }
            ) {
                Text("Login")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Don't have an account?",
                modifier = Modifier
                    .clickable {
                        navController.navigate(SIGN_UP_ROUTE) {
                            popUpTo(LOGIN_ROUTE) {
                                inclusive = true
                            }
                        }
                    }
                    .padding(8.dp)
            )
        }
    }

}