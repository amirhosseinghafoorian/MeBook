package com.example.mebook.ui.presentation.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mebook.R
import com.example.mebook.navigation.MeBookScreens.AuthNavRoute
import com.example.mebook.navigation.MeBookScreens.HomeNavRoute
import com.example.mebook.navigation.MeBookScreens.LoginRoute
import com.example.mebook.navigation.MeBookScreens.SignUpRoute
import com.example.mebook.ui.components.LottieBox
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
    val uiState by viewModel.uiState.collectAsState()

    val usernameState = remember { UsernameTextFieldState() }
    val passwordState = remember { PasswordTextFieldState() }

    var passwordVisibility by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = uiState.isLoginSuccess) {
        if (uiState.isLoginSuccess) {
            navController.navigate(HomeNavRoute.route) {
                popUpTo(AuthNavRoute.route) {
                    inclusive = true
                }
            }
        }
    }

    MeBookScaffold(
        snackbarFlow = viewModel.snackbarFlow
    ) {
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
                visualTransformation = if (passwordVisibility)
                    VisualTransformation.None
                else
                    PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = {
                        passwordVisibility = !passwordVisibility
                    }) {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = null
                        )
                    }
                },
                placeholder = "Password",
                keyboardOptions = KeyboardOptions.Default
                    .copy(keyboardType = KeyboardType.Password),
            )

            Spacer(modifier = Modifier.weight(1f))

            MeBookButton(
                enabled = !uiState.isLoading,
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    if (
                        usernameState.validate() &&
                        passwordState.validate()
                    ) {
                        viewModel.submitAction(
                            ConfirmLogin(
                                usernameState.text,
                                passwordState.text
                            )
                        )
                    }
                }
            ) {
                if (uiState.isLoading) {
                    LottieBox(
                        resourceId = R.raw.loading,
                        modifier = Modifier.size(128.dp),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Text("Login")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Don't have an account?",
                modifier = Modifier
                    .clip(MaterialTheme.shapes.medium)
                    .clickable {
                        navController.navigate(SignUpRoute.route) {
                            popUpTo(LoginRoute.route) {
                                inclusive = true
                            }
                        }
                    }
                    .padding(16.dp)
            )
        }
    }
}