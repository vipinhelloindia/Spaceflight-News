package com.feature.details.di


import com.feature.details.data.api.SpaceArticlesDetailApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class ArticleDetailApiModule {

    @ViewModelScoped
    @Provides
    fun provideSpaceDetailArticlesApi(
        retrofit: Retrofit
    ): SpaceArticlesDetailApi {
        return retrofit.create(SpaceArticlesDetailApi::class.java)
    }
}
