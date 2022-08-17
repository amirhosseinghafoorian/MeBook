package com.example.mebook.ui.presentation.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mebook.navigation.MeBookScreens.AUTH_NAV_ROUTE
import com.example.mebook.navigation.MeBookScreens.HOME_NAV_ROUTE
import com.example.mebook.navigation.MeBookScreens.LOGIN_ROUTE
import com.example.mebook.navigation.MeBookScreens.SIGN_UP_ROUTE
import com.example.mebook.ui.components.MeBookButton
import com.example.mebook.ui.components.MeBookScaffold
import com.example.mebook.ui.components.MeBookTextField
import com.example.mebook.ui.components.text_field_util.PasswordTextFieldState
import com.example.mebook.ui.components.text_field_util.UsernameTextFieldState
import com.example.mebook.ui.presentation.login.LoginAction.ConfirmLogin

@Composable
fun LoginScreen(
    navController: NavController
) {
    LoginScreen(
        navController = navController,
        viewModel = hiltViewModel()
    )
}

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel
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

            MeBookTextField(
                state = usernameState,
                onValueChange = {
                    usernameState.onChanged(it)
                },
                placeholder = "Username"
            )

            Spacer(modifier = Modifier.height(16.dp))

            MeBookTextField(
                state = passwordState,
                onValueChange = {
                    passwordState.onChanged(it)
                },
                placeholder = "Password",
                keyboardOptions = KeyboardOptions.Default
                    .copy(keyboardType = KeyboardType.Password),
            )

            Spacer(modifier = Modifier.weight(1f))

            MeBookButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    if (
                        usernameState.validate() &&
                        passwordState.validate()
                    ) {
                        // todo login api call
                        viewModel.submitAction(ConfirmLogin(usernameState.text, passwordState.text))
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
                    .clip(MaterialTheme.shapes.medium)
                    .clickable {
                        navController.navigate(SIGN_UP_ROUTE) {
                            popUpTo(LOGIN_ROUTE) {
                                inclusive = true
                            }
                        }
                    }
                    .padding(16.dp)
            )
        }
    }
}