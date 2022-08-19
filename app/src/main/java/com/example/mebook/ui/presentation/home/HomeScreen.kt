package com.example.mebook.ui.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mebook.model.view.ArticleView
import com.example.mebook.ui.components.ArticleListItem
import com.example.mebook.ui.presentation.home.HomeAction.FeaturedItemClick
import com.example.mebook.ui.presentation.home.HomeAction.FeaturedShowMore
import com.example.mebook.ui.presentation.home.HomeAction.FeedItemClick
import com.example.mebook.ui.presentation.home.HomeAction.FeedShowMore

@Composable
fun HomeScreen(
    navController: NavController
) {
    HomeScreen(
        navController = navController,
        viewModel = hiltViewModel()
    )
}

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel
) {

    val uiState by viewModel.uiState.collectAsState()

    HomeScreen(uiState) { action ->
        viewModel.submitAction(action)
    }
}

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    action: (HomeAction) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        FeedList(
            list = uiState.feed,
            showMore = {
                action(FeedShowMore)
            },
            onItemClick = {
                action(FeedItemClick(it))
            }
        )

        FeaturedList(
            list = uiState.featured,
            showMore = {
                action(FeaturedShowMore)
            },
            onItemClick = {
                action(FeaturedItemClick(it))
            }
        )

        Spacer(modifier = Modifier.height(80.dp))
    }
}

@Composable
fun FeedList(
    list: List<ArticleView>,
    showMore: () -> Unit,
    onItemClick: (Int) -> Unit
) {
    if (list.isNotEmpty()) {
        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = "For You",
                style = MaterialTheme.typography.body1.copy(
                    color = MaterialTheme.colors.secondary,
                    fontWeight = FontWeight.ExtraBold,
                )
            )
        }

        if (list.size > 5) {
            for (i in 0 until 5) {
                Spacer(modifier = Modifier.height(16.dp))

                ArticleListItem(list[i]) {
                    onItemClick(it)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(64.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .clickable { showMore() }
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Show more",
                    style = MaterialTheme.typography.h6.copy(
                        color = MaterialTheme.colors.secondary,
                    )
                )
            }
        } else {
            list.forEach { item ->
                Spacer(modifier = Modifier.height(16.dp))

                ArticleListItem(item) {
                    onItemClick(it)
                }
            }
        }
    }
}

@Composable
fun FeaturedList(
    list: List<ArticleView>,
    showMore: () -> Unit,
    onItemClick: (Int) -> Unit
) {
    if (list.isNotEmpty()) {
        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = "Featured",
                style = MaterialTheme.typography.body1.copy(
                    color = MaterialTheme.colors.secondary,
                    fontWeight = FontWeight.ExtraBold,
                )
            )
        }

        if (list.size > 5) {
            for (i in 0 until 5) {
                Spacer(modifier = Modifier.height(16.dp))

                ArticleListItem(list[i]) {
                    onItemClick(it)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(64.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .clickable {
                        showMore()
                    }
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Show more",
                    style = MaterialTheme.typography.h6.copy(
                        color = MaterialTheme.colors.secondary,
                    )
                )
            }
        } else {
            list.forEach { item ->
                Spacer(modifier = Modifier.height(16.dp))

                ArticleListItem(item) {
                    onItemClick(it)
                }
            }
        }
    }
}