package com.example.mebook.ui.presentation.article

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
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
import com.example.mebook.ui.components.ArrowBackBox
import com.example.mebook.ui.components.MeBookScaffold
import com.example.mebook.ui.presentation.article.ArticleAction.NavigateUp

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

    ArticleScreen(uiState) { action ->
        when (action) {
            NavigateUp -> navController.navigateUp()
        }
    }
}

@Composable
fun ArticleScreen(
    uiState: ArticleUiState,
    action: (ArticleAction) -> Unit
) {
    MeBookScaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            ArrowBackBox {
                action(NavigateUp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            AuthorSection {

            }

            Spacer(modifier = Modifier.height(32.dp))

            TitleSection()

            Spacer(modifier = Modifier.height(32.dp))

            BodySection()

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun AuthorSection(
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
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("AmirHossein")

            Spacer(modifier = Modifier.weight(1f))

            Text("9/1/2022")
        }
    }
}

@Composable
fun TitleSection() {
    Text(
        text = "This is a title",
        style = MaterialTheme.typography.h4,
        color = MaterialTheme.colors.onBackground,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun BodySection() {
    Text(
        text = "This is a Body",
        style = MaterialTheme.typography.h6,
        color = MaterialTheme.colors.onBackground
    )
}