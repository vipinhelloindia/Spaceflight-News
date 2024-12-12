package com.feature.listing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.feature.listing.mvi.FetchSpaceNewsIntent
import com.feature.listing.mvi.ListingScreenState
import com.spaceflight.common.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListingViewModel @Inject constructor(private val listingUseCase: ListingUseCase) :
    ViewModel() {

    private val _viewState = MutableStateFlow<ListingScreenState>(ListingScreenState.InitialState)
    val viewState: StateFlow<ListingScreenState> = _viewState

    fun handleIntent(intent: FetchSpaceNewsIntent) {
        when (intent) {
            is FetchSpaceNewsIntent.FetchSpaceNews -> fetchSpaceNews()
        }
    }

    private fun fetchSpaceNews() {
        _viewState.update { ListingScreenState.Loading }
        viewModelScope.launch {
            when (val result = listingUseCase.getFeaturedArticles(20)) {
                is Resource.Success -> {
                    _viewState.update { ListingScreenState.Success(users = result.data) }
                }

                is Resource.Failure -> {
                    _viewState.update {
                        ListingScreenState.Error(result.throwable.message!!)
                    }
                }

            }
        }

    }

}