package com.example.mebook.ui.presentation.authenticate

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mebook.navigation.MeBookScreens.LOGIN_ROUTE
import com.example.mebook.navigation.MeBookScreens.SIGN_UP_ROUTE
import com.example.mebook.ui.components.MeBookScaffold
import com.example.mebook.ui.presentation.authenticate.AuthenticateAction.CallApi
import com.example.mebook.ui.presentation.authenticate.AuthenticateAction.CallDatabase
import com.example.mebook.ui.presentation.authenticate.AuthenticateAction.Navigate
import com.example.mebook.ui.presentation.authenticate.AuthenticateAction.NavigateUp
import com.example.mebook.ui.util.doOnFalse
import com.example.mebook.ui.util.doOnTrue

@Composable
fun AuthenticateScreen(
    navController: NavController
) {
    AuthenticateScreen(
        navController = navController,
        viewModel = hiltViewModel()
    )
}

@Composable
fun AuthenticateScreen(
    navController: NavController,
    viewModel: AuthenticateViewModel
) {

    val uiState by viewModel.uiState.collectAsState()

    MeBookScaffold(
        snackbarFlow = viewModel.snackbarFlow
    ) {
        AuthenticateScreen(uiState) { action ->
            when (action) {
                NavigateUp -> navController.navigateUp()
                is Navigate -> navController.navigate(action.route)
                else -> viewModel.submitAction(action)
            }
        }
    }
}

@Composable
fun AuthenticateScreen(
    uiState: AuthenticateUiState,
    action: (AuthenticateAction) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(uiState.name)

        Spacer(modifier = Modifier.height(64.dp))

        uiState.isLoading
            .doOnTrue { CircularProgressIndicator() }
            .doOnFalse {

                Button(
                    onClick = { action(Navigate(SIGN_UP_ROUTE)) }
                ) {
                    Text("sign up")
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    colors = ButtonDefaults.buttonColors(
                        contentColor = MaterialTheme.colors.surface,
                        backgroundColor = MaterialTheme.colors.secondary,
                        disabledBackgroundColor = MaterialTheme.colors.secondary
                            .copy(alpha = ContentAlpha.disabled)
                    ),
                    onClick = { action(Navigate(LOGIN_ROUTE)) }
                ) {
                    Text("login")
                }
            }
    }
}