package com.feature.listing

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Home
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.feature.listing.model.Article
import com.feature.listing.mvi.FetchSpaceNewsIntent
import com.feature.listing.mvi.ListingScreenState
import com.spaceflight.common.ConnectionState
import com.spaceflight.common.isInternetAvailable
import com.spaceflight.design.font12Sp
import com.spaceflight.design.font14Sp
import com.spaceflight.design.font16Sp
import com.spaceflight.design.height2Dp
import com.spaceflight.design.padding4dp
import com.spaceflight.design.screenPadding16dp
import com.spaceflight.design.ui.Appbar
import com.spaceflight.design.ui.LoadingIndicator
import com.spaceflight.design.ui.MessageScreen
import com.spaceflight.design.ui.NoInternetScreen
import com.spaceflight.design.width2Dp


@Composable
fun SpaceNewsListScreen(
    viewModel: ListingViewModel = hiltViewModel(),
    onEvent: (String) -> Unit
) {
    val uiState by viewModel.viewState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        viewModel.handleIntent(FetchSpaceNewsIntent.FetchSpaceNews)
    }

    Users(uiState = uiState, context.isInternetAvailable(), onEvent)

}

@Composable
fun Users(
    uiState: ListingScreenState,
    connectionState: ConnectionState,
    onEvent: (String) -> Unit
) {

    Scaffold(topBar = {
        Appbar(
            title = stringResource(R.string.space_flight),
            navIcon = Icons.TwoTone.Home,
        )
    }, content = { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)

        ) {
            if (connectionState is ConnectionState.Unavailable) {
                NoInternetScreen()
            } else {
                when (uiState) {
                    is ListingScreenState.InitialState -> MessageScreen(
                        textMessage = stringResource(
                            R.string.no_flight_news
                        )
                    )

                    is ListingScreenState.Loading -> LoadingIndicator()
                    is ListingScreenState.Error -> MessageScreen(uiState.message)
                    is ListingScreenState.Success -> SpaceFlightNewsListing(uiState.users, onEvent)
                }
            }
        }
    })
}

@Composable
fun SpaceFlightNewsListing(articles: List<Article>, onEvent: (String) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(padding4dp),
        verticalArrangement = Arrangement.spacedBy(padding4dp)
    ) {
        itemsIndexed(
            items = articles,
            key = { _, item -> item.id }
        ) { _, item ->
            SpaceFlightNewsItem(article = item, onEvent)
        }
    }
}

@Composable
fun SpaceFlightNewsItem(article: Article, onEvent: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(screenPadding16dp)
            .clickable {
                onEvent(article.id)
            },
    ) {
        Spacer(modifier = Modifier.width(width2Dp))
        Text(
            text = article.title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            fontSize = font16Sp()
        )

        Spacer(modifier = Modifier.height(height2Dp))

        Text(
            text = article.summary,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            fontSize = font14Sp()
        )

        Spacer(modifier = Modifier.height(height2Dp))

        Text(
            text = article.publishedAt,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontSize = font12Sp()
        )
    }

}

