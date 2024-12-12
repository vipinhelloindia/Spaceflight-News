package com.feature.listing.di

import com.feature.listing.data.api.SpaceArticlesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class ListingApiModule {

    @ViewModelScoped
    @Provides
    fun provideSpaceArticlesApi(
        retrofit: Retrofit
    ): SpaceArticlesApi {
        return retrofit.create(SpaceArticlesApi::class.java)
    }
}
