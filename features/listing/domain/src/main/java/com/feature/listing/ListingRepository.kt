package com.feature.listing

import com.feature.listing.model.Article
import com.spaceflight.common.Resource

interface ListingRepository {

    suspend fun getFeaturedArticles(
        hasEvent: Boolean = true,
        hasLaunch: Boolean = true,
        isFeatured: Boolean = true,
        limit: Int = 10
    ): Resource<List<Article>>
}