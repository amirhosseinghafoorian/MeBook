package com.example.mebook.ui.presentation.search

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mebook.R
import com.example.mebook.model.view.UserItemView
import com.example.mebook.ui.components.LottieBox
import com.example.mebook.ui.components.MeBookTextField
import com.example.mebook.ui.components.UserList
import com.example.mebook.ui.components.rememberAnimationState
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

    SearchScreen(uiState) { action ->
        viewModel.submitAction(action)
    }
}

@Composable
fun SearchScreen(
    uiState: SearchUiState,
    action: (SearchAction) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        MeBookTextField(
            placeholder = "Search for Users",
            state = uiState.searchState,
            onValueChange = {
                action(UpdateTextField(it))
            }
        )

        if (uiState.isLoading) {
            Spacer(modifier = Modifier.weight(1f))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LottieBox(
                    resourceId = R.raw.loading,
                    modifier = Modifier.size(128.dp)
                )
            }

            Spacer(modifier = Modifier.weight(1f))
        } else {
            SearchedUsersList(uiState.searchUsers) {
                // todo navigate to user profile
            }
        }
    }
}

@Composable
fun ColumnScope.SearchedUsersList(
    list: List<UserItemView>,
    onItemClick: (String) -> Unit
) {
    if (list.isNotEmpty()) {
        Spacer(modifier = Modifier.height(16.dp))

        UserList(list) { username ->
            onItemClick(username)
        }
    } else {
        Spacer(modifier = Modifier.weight(0.1f))

        Column(
            modifier = Modifier.weight(0.6f)
        ) {
            AnimatedVisibility(rememberAnimationState(), enter = fadeIn(tween(1000))) {
                LottieBox(
                    resourceId = R.raw.empty, // todo this is a bad lottie and should be replaced
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
        Spacer(modifier = Modifier.weight(0.3f))
    }
}