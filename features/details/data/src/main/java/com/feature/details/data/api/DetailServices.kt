package com.feature.details.data.api

import com.feature.details.data.repo.model.ArticleDetailDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SpaceArticlesDetailApi {
    @GET("v4/articles/{id}/")
    suspend fun getArticleById(
        @Path("id") articleId: String
    ): Response<ArticleDetailDto>
}