package com.example.mebook.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.mebook.model.view.ArticleItemView
import com.example.mebook.ui.util.timestampToDate

@Composable
fun ArticleListItem(
    item: ArticleItemView,
    onItemClick: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .requiredHeight(128.dp)
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colors.surface)
            .clickable { onItemClick(item.articleId) }
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.25f)
        ) {
            Text(
                softWrap = false,
                overflow = TextOverflow.Ellipsis,
                text = item.authorUsername,
                style = MaterialTheme.typography.body1.copy(
                    color = MaterialTheme.colors.onSurface
                ),
                modifier = Modifier.weight(0.6f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = timestampToDate(item.publishDate),
                style = MaterialTheme.typography.body1.copy(
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f)
                ),
                modifier = Modifier.weight(0.4f)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            text = item.title,
            style = MaterialTheme.typography.h6.copy(
                color = MaterialTheme.colors.onSurface,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.weight(0.75f)
        )
    }
}