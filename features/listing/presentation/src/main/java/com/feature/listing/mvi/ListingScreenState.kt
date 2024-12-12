package com.feature.listing.mvi

import com.feature.listing.model.Article

sealed interface ListingScreenState {
    object InitialState : ListingScreenState
    object Loading : ListingScreenState
    class Success(val users: List<Article>) : ListingScreenState
    class Error(val message: String) : ListingScreenState
}
