package com.example.mebook.feature.presentation.authenticate

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mebook.MeBookScreens.LOGIN_ROUTE
import com.example.mebook.MeBookScreens.SIGN_UP_ROUTE
import com.example.mebook.feature.util.Test
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

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
    viewModel: AuthenticateViewModel,
    snackBarHostState: SnackbarHostState = SnackbarHostState()
) {
    SnackbarHost(snackBarHostState)
    val scope = rememberCoroutineScope()

    SideEffect {
        scope.launch {
            viewModel.snackBarState.collectLatest { message ->
                snackBarHostState.showSnackbar(message)
            }
        }
    }

    val uiState by viewModel.uiState.collectAsState()

    AuthenticateScreen(
        navController = navController,
        uiState = uiState
    ) { action ->
        // todo should be completed with screen actions
    }
}

@Composable
fun AuthenticateScreen(
    navController: NavController, // should be removed and replaced with action
    uiState: Test,
    action: (Test) -> Unit
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