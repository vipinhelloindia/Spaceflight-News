package com.feature.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import com.feature.details.model.ArticleDetail
import com.feature.details.mvi.ArticleDetailIntent
import com.feature.details.mvi.DetailScreenState
import com.spaceflight.common.ConnectionState
import com.spaceflight.common.isInternetAvailable
import com.spaceflight.design.font16Sp
import com.spaceflight.design.font18Sp
import com.spaceflight.design.height2Dp
import com.spaceflight.design.screenPadding16dp
import com.spaceflight.design.ui.Appbar
import com.spaceflight.design.ui.LoadingIndicator
import com.spaceflight.design.ui.MessageScreen
import com.spaceflight.design.ui.NoInternetScreen
import com.spaceflight.design.width2Dp


@Composable
fun ArticleDetailScreen(
    articleId: String, viewModel: ArticleDetailViewModel = hiltViewModel(), onNavigation: () -> Unit
) {
    val uiState by viewModel.viewState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        viewModel.handleIntent(ArticleDetailIntent.FetchArticleDetail(articleId))
    }
    ArticleDetailUI(uiState, context.isInternetAvailable(), onNavigation)

}

@Composable
fun ArticleDetailUI(
    uiState: DetailScreenState, connectionState: ConnectionState, onNavigation: () -> Unit
) {
    Scaffold(topBar = {
        Appbar(title = stringResource(R.string.article_detail),
            navIcon = Icons.AutoMirrored.Filled.ArrowBack,
            onNav = {
                onNavigation()
            })
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
                    is DetailScreenState.InitialState -> MessageScreen(
                        textMessage = stringResource(
                            R.string.no_flight_news
                        )
                    )

                    is DetailScreenState.Loading -> LoadingIndicator()
                    is DetailScreenState.Error -> MessageScreen(uiState.errorMessage)
                    is DetailScreenState.Success -> ArticleUI(uiState.detail)
                }
            }

        }
    })

}

@Composable
fun ArticleUI(articleDetail: ArticleDetail) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(screenPadding16dp)

    ) {
        Spacer(modifier = Modifier.width(width2Dp))
        Text(
            text = articleDetail.title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 4,
            overflow = TextOverflow.Ellipsis,
            fontSize = font18Sp()
        )
        Spacer(modifier = Modifier.height(height2Dp))
        Text(
            text = articleDetail.summary,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            overflow = TextOverflow.Ellipsis,
            fontSize = font16Sp()
        )
    }
}
