package com.feature.listing.di

import com.feature.listing.ListingRepository
import com.feature.listing.ListingUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class ListingUseCaseModule {

    @ViewModelScoped
    @Provides
    fun provideListingUseCase(
        listingRepository: ListingRepository,
    ): ListingUseCase {
        return ListingUseCase(listingRepository)
    }
}
