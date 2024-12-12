package com.feature.listing.mvi

sealed interface FetchSpaceNewsIntent {
    data object FetchSpaceNews : FetchSpaceNewsIntent
}