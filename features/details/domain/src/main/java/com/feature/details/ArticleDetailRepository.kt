package com.feature.details

import com.feature.details.model.ArticleDetail
import com.spaceflight.common.Resource

interface ArticleDetailRepository {

    suspend fun getFeaturedArticles(
        articleId: String
    ): Resource<ArticleDetail>
}