package com.example.mebook.ui.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mebook.ui.components.MeBookTextField
import com.example.mebook.ui.presentation.search.SearchAction.UpdateTextField

@Composable
fun SearchScreen(
    navController: NavController
) {
    SearchScreen(
        navController = navController,
        viewModel = hiltViewModel()
    )
}

@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchViewModel
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        MeBookTextField(
            placeholder = "Search for Users",
            state = uiState.searchState,
            onValueChange = {
                viewModel.submitAction(UpdateTextField(it))
            }
        )
    }
}