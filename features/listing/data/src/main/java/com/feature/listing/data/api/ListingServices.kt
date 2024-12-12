package com.feature.listing.data.api

import com.feature.listing.data.repo.model.ArticleListDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SpaceArticlesApi {
    @GET("v4/articles/")
    suspend fun getFeaturedArticles(
        @Query("has_event") hasEvent: Boolean = true,
        @Query("has_launch") hasLaunch: Boolean = true,
        @Query("is_featured") isFeatured: Boolean = true,
        @Query("limit") limit: Int = 10
    ): Response<ArticleListDto>
}