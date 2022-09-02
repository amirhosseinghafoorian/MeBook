package com.example.mebook.ui.presentation.write

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mebook.navigation.MeBookScreens
import com.example.mebook.ui.components.ArrowBackBox
import com.example.mebook.ui.components.MeBookScaffold
import com.example.mebook.ui.presentation.write.WriteAction.NavigateUp

@Composable
fun WriteScreen(
    navController: NavController
) {
    WriteScreen(
        navController = navController,
        viewModel = hiltViewModel()
    )
}

@Composable
fun WriteScreen(
    navController: NavController,
    viewModel: WriteViewModel
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(uiState.isPublished) {
        if (uiState.isPublished) {
            navController.navigate(MeBookScreens.HomeNavRoute.route) {
                popUpTo(MeBookScreens.HomeNavRoute.route) {
                    inclusive = true
                }
            }
        }
    }

    // todo add a dialog that warns user about discarding changes
    WriteScreen(uiState) { action ->
        when (action) {
            NavigateUp -> navController.navigateUp()
            else -> viewModel.submitAction(action)
        }
    }

}

@Composable
fun WriteScreen(
    uiState: WriteUiState,
    action: (WriteAction) -> Unit
) {
    MeBookScaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            ArrowBackBox {
                action(NavigateUp)
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}