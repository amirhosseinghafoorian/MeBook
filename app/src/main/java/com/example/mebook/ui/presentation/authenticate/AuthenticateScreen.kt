package com.example.mebook.ui.presentation.authenticate

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mebook.R
import com.example.mebook.navigation.MeBookScreens.LOGIN_ROUTE
import com.example.mebook.navigation.MeBookScreens.SIGN_UP_ROUTE
import com.example.mebook.ui.components.LottieBox
import com.example.mebook.ui.components.MeBookButton
import com.example.mebook.ui.components.MeBookScaffold
import com.example.mebook.ui.components.rememberAnimationState
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
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Get Started\nSign into your MeBook Account",
            style = MaterialTheme.typography.h6.copy(
                color = MaterialTheme.colors.secondary,
                fontWeight = FontWeight.Bold
            ),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            AnimatedVisibility(rememberAnimationState(), enter = fadeIn(tween(2000))) {
                LottieBox(
                    resourceId = R.raw.book_lover_lottie,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        uiState.isLoading
            .doOnTrue { CircularProgressIndicator() }
            .doOnFalse {

                MeBookButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { action(Navigate(SIGN_UP_ROUTE)) }
                ) {
                    Text("sign up")
                }

                Spacer(modifier = Modifier.height(16.dp))

                MeBookButton(
                    modifier = Modifier.fillMaxWidth(),
                    backgroundColor = MaterialTheme.colors.secondary,
                    onClick = { action(Navigate(LOGIN_ROUTE)) }
                ) {
                    Text("login")
                }
            }
    }
}