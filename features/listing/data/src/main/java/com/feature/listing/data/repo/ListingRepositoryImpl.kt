package com.feature.listing.data.repo

import com.feature.listing.ListingRepository
import com.feature.listing.data.api.SpaceArticlesApi
import com.feature.listing.data.mapper.toArticle
import com.feature.listing.model.Article
import com.spaceflight.common.Resource
import com.spaceflight.network.retrofitApiCall
import javax.inject.Inject

class ListingRepositoryImpl @Inject constructor(private val spaceArticlesApi: SpaceArticlesApi) :
    ListingRepository {

    override suspend fun getFeaturedArticles(
        hasEvent: Boolean,
        hasLaunch: Boolean,
        isFeatured: Boolean,
        limit: Int,
    ): Resource<List<Article>> {
        return retrofitApiCall(
            apiCall = {
                spaceArticlesApi.getFeaturedArticles(
                    hasEvent = hasEvent,
                    hasLaunch = hasLaunch,
                    isFeatured = isFeatured,
                    limit = limit
                )
            },
            mapper = { articles ->
                articles.results.map {
                    it.toArticle()
                }
            }
        )
    }
}