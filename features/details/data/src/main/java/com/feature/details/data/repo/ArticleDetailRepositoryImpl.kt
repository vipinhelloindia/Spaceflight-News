package com.feature.details.data.repo

import com.feature.details.ArticleDetailRepository
import com.feature.details.data.api.SpaceArticlesDetailApi
import com.feature.details.data.mapper.toDetailArticle
import com.feature.details.model.ArticleDetail
import com.spaceflight.common.Resource
import com.spaceflight.network.retrofitApiCall
import javax.inject.Inject


class ArticleDetailRepositoryImpl @Inject constructor(private val spaceArticlesDetailApi: SpaceArticlesDetailApi) :
    ArticleDetailRepository {

    override suspend fun getFeaturedArticles(
        articleId: String,
    ): Resource<ArticleDetail> {
        return retrofitApiCall(
            apiCall = { spaceArticlesDetailApi.getArticleById(articleId) },
            mapper = { articleDetails ->
                articleDetails.toDetailArticle()
            })
    }

}