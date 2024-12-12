package com.feature.details.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.feature.details.ArticleDetailUI
import com.feature.details.model.ArticleDetail
import com.feature.details.mvi.DetailScreenState
import com.spaceflight.common.ConnectionState


val mockArticleDetail = ArticleDetail(
    id = 1,
    title = "The Mysteries of Space Exploration",
    imageUrl = "https://via.placeholder.com/150",
    publishedAt = "2024-11-25",
    summary = "Space exploration reveals mysteries of the universe and challenges our understanding of physics."
)

// Mock states
val loadingState = DetailScreenState.Loading

val successState = DetailScreenState.Success(mockArticleDetail)

val errorState = DetailScreenState.Error("")

@Preview
@Composable
fun ArticleDetailUIPreview() {
    ArticleDetailUI(successState, ConnectionState.Available, {})
}