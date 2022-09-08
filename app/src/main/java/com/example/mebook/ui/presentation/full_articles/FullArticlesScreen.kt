package com.example.mebook.ui.presentation.full_articles

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mebook.navigation.MeBookScreens
import com.example.mebook.ui.components.ArrowBackBox
import com.example.mebook.ui.components.ArticleList
import com.example.mebook.ui.components.MeBookScaffold
import com.example.mebook.ui.components.ShowMoreItem
import com.example.mebook.ui.presentation.full_articles.FullArticlesAction.NavigateUp
import com.example.mebook.ui.presentation.full_articles.FullArticlesAction.OnItemClick
import com.example.mebook.ui.presentation.full_articles.FullArticlesAction.ShowMore

@Composable
fun FullArticlesScreen(
    navController: NavController
) {
    FullArticlesScreen(
        navController = navController,
        viewModel = hiltViewModel()
    )
}

@Composable
fun FullArticlesScreen(
    navController: NavController,
    viewModel: FullArticlesViewModel
) {
    val uiState by viewModel.uiState.collectAsState()

    FullArticlesScreen(uiState) { action ->
        when (action) {
            is OnItemClick -> navController.navigate(
                MeBookScreens.ArticleRoute.generateRoute(action.id)
            )
            is NavigateUp -> navController.navigateUp()
            else -> viewModel.submitAction(action)
        }
    }

}

@Composable
fun FullArticlesScreen(
    uiState: FullArticlesUiState,
    action: (FullArticlesAction) -> Unit
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

            ArticleList(uiState.articles) { id ->
                action(OnItemClick(id))
            }

            if (uiState.articles.isNotEmpty() && uiState.canShowMore) {
                Spacer(modifier = Modifier.height(16.dp))

                ShowMoreItem {
                    action(ShowMore)
                }
            }
        }
    }
}