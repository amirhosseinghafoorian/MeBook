package com.example.mebook.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mebook.model.view.ArticleItemView

@Composable
fun ArticleList(
    articles: List<ArticleItemView>,
    onItemClick: (Int) -> Unit
) {
    // todo should be replaced with lazyColumn with key
    articles.forEach { item ->
        Spacer(modifier = Modifier.height(16.dp))

        ArticleListItem(item) {
            onItemClick(it)
        }
    }
}