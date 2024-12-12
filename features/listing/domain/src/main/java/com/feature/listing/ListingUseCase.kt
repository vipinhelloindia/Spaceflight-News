package com.feature.listing

import com.feature.listing.model.Article
import com.spaceflight.common.Resource
import javax.inject.Inject

class ListingUseCase @Inject constructor(private val listingRepository: ListingRepository) {

    suspend fun getFeaturedArticles(noOfArticle: Int): Resource<List<Article>> =
        listingRepository.getFeaturedArticles(limit = noOfArticle)

}