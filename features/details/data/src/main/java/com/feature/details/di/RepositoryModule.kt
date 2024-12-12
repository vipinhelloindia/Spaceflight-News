package com.feature.details.di

import com.feature.details.ArticleDetailRepository
import com.feature.details.data.repo.ArticleDetailRepositoryImpl
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
        listingRepository: ArticleDetailRepositoryImpl
    ): ArticleDetailRepository
}
