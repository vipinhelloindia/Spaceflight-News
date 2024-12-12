package com.feature.details

import javax.inject.Inject

class ArticleDetailUseCase @Inject constructor(private val articleDetailRepository: ArticleDetailRepository) {

    suspend fun getArticleDetail(articleID: String) =
        articleDetailRepository.getFeaturedArticles(articleID)

}