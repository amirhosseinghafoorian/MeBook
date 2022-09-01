package com.example.mebook.ui.presentation.article

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mebook.R
import com.example.mebook.navigation.MeBookScreens
import com.example.mebook.navigation.MeBookScreens.HomeNavRoute
import com.example.mebook.ui.components.ArrowBackBox
import com.example.mebook.ui.components.LottieBox
import com.example.mebook.ui.components.MeBookButton
import com.example.mebook.ui.components.MeBookScaffold
import com.example.mebook.ui.presentation.article.ArticleAction.DeleteArticle
import com.example.mebook.ui.presentation.article.ArticleAction.NavigateToUserProfile
import com.example.mebook.ui.presentation.article.ArticleAction.NavigateUp
import com.example.mebook.ui.util.doOnFalse
import com.example.mebook.ui.util.doOnTrue
import kotlinx.coroutines.launch

@Composable
fun ArticleScreen(
    navController: NavController
) {
    ArticleScreen(
        navController = navController,
        viewModel = hiltViewModel()
    )
}

@Composable
fun ArticleScreen(
    navController: NavController,
    viewModel: ArticleViewModel
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(uiState.isDeleted) {
        if (uiState.isDeleted) {
            navController.navigate(HomeNavRoute.route) {
                popUpTo(HomeNavRoute.route) {
                    inclusive = true
                }
            }
        }
    }

    ArticleScreen(uiState) { action ->
        when (action) {
            NavigateUp -> navController.navigateUp()
            is NavigateToUserProfile -> navController.navigate(
                MeBookScreens.ProfileRoute.generateRoute(action.username)
            )
            else -> viewModel.submitAction(action)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ArticleScreen(
    uiState: ArticleUiState,
    action: (ArticleAction) -> Unit
) {
    val modalBottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )

    val scope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        sheetContent = {
            DeleteBottomSheet {
                action(DeleteArticle)
            }
        },
        sheetBackgroundColor = MaterialTheme.colors.background,
        sheetState = modalBottomSheetState,
        sheetShape = MaterialTheme.shapes.medium.copy(
            bottomEnd = CornerSize(0.dp),
            bottomStart = CornerSize(0.dp)
        )
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

                uiState.isLoadingArticle.doOnTrue {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        LottieBox(
                            resourceId = R.raw.loading,
                            modifier = Modifier.size(128.dp),
                            contentScale = ContentScale.Crop
                        )
                    }
                }.doOnFalse {
                    uiState.article?.let { article ->
                        AuthorSection(article.authorUsername, article.publishDate) {
                            action(NavigateToUserProfile(article.authorUsername))
                        }

                        Spacer(modifier = Modifier.height(32.dp))

                        TitleSection(article.title)

                        Spacer(modifier = Modifier.height(32.dp))

                        BodySection(article.body)
                    }
                }

                uiState.isOwnArticle?.let { isOwn ->
                    isOwn.doOnTrue {
                        Spacer(modifier = Modifier.height(64.dp))

                        MeBookButton(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 32.dp),
                            shape = MaterialTheme.shapes.large,
                            backgroundColor = MaterialTheme.colors.error,
                            onClick = {
                                scope.launch {
                                    modalBottomSheetState.show()
                                }
                            }
                        ) {
                            Text(
                                text = "Delete Article",
                                style = MaterialTheme.typography.body1,
                                color = MaterialTheme.colors.surface
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }

}

@Composable
fun AuthorSection(
    author: String,
    publishDate: Long,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 64.dp)
    ) {
        Spacer(
            modifier = Modifier
                .width(4.dp)
                .fillMaxHeight()
                .background(MaterialTheme.colors.primary)
        )

        Row(
            modifier = Modifier
                .fillMaxSize()
                .clip(
                    MaterialTheme.shapes.large.copy(
                        topStart = CornerSize(0.dp),
                        bottomStart = CornerSize(0.dp),
                    )
                )
                .background(MaterialTheme.colors.surface)
                .clickable { onClick() }
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(author)

            Spacer(modifier = Modifier.weight(1f))

            Text(publishDate.toString())
        }
    }
}

@Composable
fun TitleSection(
    title: String
) {
    Text(
        text = title,
        style = MaterialTheme.typography.h4,
        color = MaterialTheme.colors.onBackground,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun BodySection(
    body: String
) {
    Text(
        text = body,
        style = MaterialTheme.typography.h6,
        color = MaterialTheme.colors.onBackground
    )
}

@Composable
fun DeleteBottomSheet(
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Do you want to delete this article ?",
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.onBackground,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        MeBookButton(
            backgroundColor = MaterialTheme.colors.surface,
            contentColor = MaterialTheme.colors.error,
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                onClick()
            }
        ) {
            Text("Confirm")
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}