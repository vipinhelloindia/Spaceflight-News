package com.feature.listing.di

import com.feature.listing.ListingRepository
import com.feature.listing.data.repo.ListingRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @ViewModelScoped
    @Binds
    abstract fun bindListingRepository(
        articleR: ListingRepositoryImpl
    ): ListingRepository
}
