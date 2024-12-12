package com.feature.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.feature.details.mvi.ArticleDetailIntent
import com.feature.details.mvi.DetailScreenState
import com.spaceflight.common.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleDetailViewModel @Inject constructor(private val detailUseCase: ArticleDetailUseCase) :
    ViewModel() {

    private val _viewState = MutableStateFlow<DetailScreenState>(DetailScreenState.InitialState)
    val viewState: StateFlow<DetailScreenState> = _viewState

    fun handleIntent(intent: ArticleDetailIntent) {
        when (intent) {
            is ArticleDetailIntent.FetchArticleDetail -> fetchArticleDetail(intent.articleId)
        }
    }

    private fun fetchArticleDetail(articleId: String) {
        _viewState.update { DetailScreenState.Loading }
        viewModelScope.launch {
            when (val result = detailUseCase.getArticleDetail(articleId)) {
                is Resource.Success -> {
                    _viewState.update { DetailScreenState.Success(result.data) }
                }

                is Resource.Failure -> {
                    _viewState.update { DetailScreenState.Error(result.throwable.message!!) }
                }

            }
        }
    }

}