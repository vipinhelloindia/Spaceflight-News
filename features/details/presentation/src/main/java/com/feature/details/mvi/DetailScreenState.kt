package com.feature.details.mvi

import com.feature.details.model.ArticleDetail

sealed interface DetailScreenState {
    data object InitialState : DetailScreenState
    data object Loading : DetailScreenState
    data class Success(val detail: ArticleDetail) : DetailScreenState
    data class Error(val errorMessage: String) : DetailScreenState
}
