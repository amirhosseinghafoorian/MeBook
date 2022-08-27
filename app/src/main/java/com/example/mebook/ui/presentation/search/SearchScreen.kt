package com.example.mebook.ui.presentation.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.example.mebook.model.view.UserItemView
import com.example.mebook.ui.components.MeBookTextField
import com.example.mebook.ui.components.UserList
import com.example.mebook.ui.presentation.search.SearchAction.UpdateTextField
import com.example.mebook.ui.util.doOnFalse
import com.example.mebook.ui.util.doOnTrue

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

    // todo .verticalScroll(rememberScrollState()) needed
    //  column height has a problem
    //  items does not go under bottom bar
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

        SearchedUsersList(uiState.searchUsers) {
            // todo navigate to user profile
        }
    }
}

@Composable
fun SearchedUsersList(
    list: List<UserItemView>,
    onItemClick: (String) -> Unit
) {
    list.isNotEmpty().doOnTrue {
        Spacer(modifier = Modifier.height(16.dp))

        UserList(list) { username ->
            onItemClick(username)
        }
    }.doOnFalse {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Type username to search user",
                style = MaterialTheme.typography.subtitle1.copy(
                    color = MaterialTheme.colors.onSurface
                )
            )
        }
    }
}