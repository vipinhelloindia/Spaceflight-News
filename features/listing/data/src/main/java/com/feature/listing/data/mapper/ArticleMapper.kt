package com.feature.listing.data.mapper

import com.feature.listing.Utils
import com.feature.listing.data.repo.model.ArticleDto
import com.feature.listing.model.Article

fun ArticleDto.toArticle(): Article {
    return Article(
        id = this.id.toString(),
        title = this.title,
        imageUrl = this.image_url,
        publishedAt = Utils.convertReadableDate(this.published_at) + "",
        summary = this.summary

    )
}