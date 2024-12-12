package com.feature.details.di

import com.feature.details.ArticleDetailRepository
import com.feature.details.ArticleDetailUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class DetailUseCaseModule {

    @ViewModelScoped
    @Provides
    fun provideListingUseCase(
        articleDetailRepository: ArticleDetailRepository,
    ): ArticleDetailUseCase {
        return ArticleDetailUseCase(articleDetailRepository)
    }
}
