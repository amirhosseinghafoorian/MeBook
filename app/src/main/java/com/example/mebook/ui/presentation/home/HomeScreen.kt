package com.example.mebook.ui.presentation.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mebook.AppConstants.FULL_ARTICLE_FEATURED_TYPE
import com.example.mebook.AppConstants.FULL_ARTICLE_FEED_TYPE
import com.example.mebook.R
import com.example.mebook.model.view.ArticleItemView
import com.example.mebook.navigation.MeBookScreens.FullArticlesRoute
import com.example.mebook.ui.components.AnimationStateReactor
import com.example.mebook.ui.components.ArticleList
import com.example.mebook.ui.components.LottieBox
import com.example.mebook.ui.components.MeBookSnackbarObserver
import com.example.mebook.ui.components.ShowMoreItem
import com.example.mebook.ui.components.rememberAnimationState
import com.example.mebook.ui.presentation.home.HomeAction.FeaturedItemClick
import com.example.mebook.ui.presentation.home.HomeAction.FeaturedShowMore
import com.example.mebook.ui.presentation.home.HomeAction.FeedItemClick
import com.example.mebook.ui.presentation.home.HomeAction.FeedShowMore
import com.example.mebook.ui.util.doOnTrue

@Composable
fun HomeScreen(
    navController: NavController,
    scaffoldState: ScaffoldState,
) {
    HomeScreen(
        navController = navController,
        scaffoldState = scaffoldState,
        viewModel = hiltViewModel()
    )
}

@Composable
fun HomeScreen(
    navController: NavController,
    scaffoldState: ScaffoldState,
    viewModel: HomeViewModel
) {
    MeBookSnackbarObserver(
        scaffoldState = scaffoldState,
        snackbarFlow = viewModel.snackbarFlow
    )

    val uiState by viewModel.uiState.collectAsState()

    HomeScreen(uiState) { action ->
        when (action) {
            is FeedItemClick -> {
                // todo navigate to article screen
            }
            is FeaturedItemClick -> {
                // todo navigate to article screen
            }
            is FeedShowMore -> {
                navController.navigate(FullArticlesRoute.generateRoute(FULL_ARTICLE_FEED_TYPE))
            }
            is FeaturedShowMore -> {
                navController.navigate(FullArticlesRoute.generateRoute(FULL_ARTICLE_FEATURED_TYPE))
            }
        }
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
        val animationState = rememberAnimationState()
        AnimationStateReactor(animationState, uiState.isLoading)
        AnimatedVisibility(animationState, enter = fadeIn(tween())) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 32.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                LottieBox(
                    resourceId = R.raw.loading,
                    modifier = Modifier.size(128.dp),
                    contentScale = ContentScale.Crop
                )
            }
        }

        FeedList(
            list = uiState.feed,
            canShowMore = uiState.canShowMoreFeed,
            showMore = {
                action(FeedShowMore)
            },
            onItemClick = {
                action(FeedItemClick(it))
            }
        )

        FeaturedList(
            list = uiState.featured,
            canShowMore = uiState.canShowMoreFeatured,
            showMore = {
                action(FeaturedShowMore)
            },
            onItemClick = {
                action(FeaturedItemClick(it))
            }
        )

        Spacer(modifier = Modifier.height(64.dp))
    }
}

@Composable
fun FeedList(
    list: List<ArticleItemView>,
    canShowMore: Boolean,
    showMore: () -> Unit,
    onItemClick: (Int) -> Unit
) {
    list.isNotEmpty().doOnTrue {
        Spacer(modifier = Modifier.height(16.dp))

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

        ArticleList(list) { id ->
            onItemClick(id)
        }

        canShowMore.doOnTrue {
            Spacer(modifier = Modifier.height(16.dp))

            ShowMoreItem {
                showMore()
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun FeaturedList(
    list: List<ArticleItemView>,
    canShowMore: Boolean,
    showMore: () -> Unit,
    onItemClick: (Int) -> Unit
) {
    list.isNotEmpty().doOnTrue {
        Spacer(modifier = Modifier.height(16.dp))

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

        ArticleList(list) { id ->
            onItemClick(id)
        }

        canShowMore.doOnTrue {
            Spacer(modifier = Modifier.height(16.dp))

            ShowMoreItem {
                showMore()
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}