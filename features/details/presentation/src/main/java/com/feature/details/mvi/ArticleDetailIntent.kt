package com.feature.details.mvi

sealed interface ArticleDetailIntent {
    data class FetchArticleDetail(val articleId: String) : ArticleDetailIntent
}
