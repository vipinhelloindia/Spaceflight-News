package com.feature.details.data.mapper

import com.feature.details.data.repo.model.ArticleDetailDto
import com.feature.details.model.ArticleDetail

fun ArticleDetailDto.toDetailArticle(): ArticleDetail {
    return ArticleDetail(
        id = this.id,
        title = this.title,
        imageUrl = this.image_url,
        publishedAt = this.published_at,
        summary = this.summary

    )
}