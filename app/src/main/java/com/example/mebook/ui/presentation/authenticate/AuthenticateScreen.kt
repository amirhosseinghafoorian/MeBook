package com.example.mebook.ui.presentation.authenticate

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mebook.ui.components.MeBookSnackbarHost
import com.example.mebook.ui.components.MeBookSnackbarObserver
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
    val scaffoldState = rememberScaffoldState()

    MeBookSnackbarObserver(
        scaffoldState = scaffoldState,
        snackbarFlow = viewModel.snackbarFlow
    )

    val uiState by viewModel.uiState.collectAsState()

    AuthenticateScreen(
        isLoading = uiState.isLoading,
        scaffoldState = scaffoldState,
        uiState = uiState
    ) { action ->
        when (action) {
            NavigateUp -> navController.navigateUp()
            is Navigate -> navController.navigate(action.route)
            else -> viewModel.submitAction(action)
        }
    }

}

@Composable
fun AuthenticateScreen(
    isLoading: Boolean,
    scaffoldState: ScaffoldState,
    uiState: AuthenticateUiState,
    action: (AuthenticateAction) -> Unit
) {
    Scaffold(
        scaffoldState = scaffoldState,
        snackbarHost = { MeBookSnackbarHost(hostState = it) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(uiState.name)

            Spacer(modifier = Modifier.height(64.dp))

            isLoading
                .doOnTrue { CircularProgressIndicator() }
                .doOnFalse {
                    Button(onClick = {
                        action(CallApi)
                    }) {
                        Text("Make Api Call")
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(onClick = {
                        action(CallDatabase)
                    }) {
                        Text("Make Database Call")
                    }
                }
        }
    }
}