package com.feature.listing.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.feature.listing.Users
import com.feature.listing.model.Article
import com.feature.listing.mvi.ListingScreenState
import com.spaceflight.common.ConnectionState


@Preview(showBackground = true)
@Composable
fun SpaceNewsListScreenPreview() {
    // Preview the Composable
    Users(
        uiState = mockUiState,
        ConnectionState.Available,
        onEvent = { }
    )
}

// Mock data for the UI state
val mockUiState = ListingScreenState.Success(
    users = listOf(
        Article(
            id = "1",
            title = "Breaking Space News!",
            imageUrl = "https://via.placeholder.com/150",
            publishedAt = "2024-11-25",
            summary = "Astronauts discover a new planet in a neighboring solar system."
        ),
        Article(
            id = "2",
            title = "Satellite Launch Success",
            imageUrl = "https://via.placeholder.com/150",
            publishedAt = "2024-11-24",
            summary = "The latest satellite launch was a complete success, providing better internet for remote regions."
        )
    )
)