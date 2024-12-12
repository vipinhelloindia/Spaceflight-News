package com.feature.listing

import com.feature.listing.data.api.SpaceArticlesApi
import com.feature.listing.data.repo.ListingRepositoryImpl
import com.feature.listing.data.repo.model.ArticleDto
import com.feature.listing.data.repo.model.ArticleListDto
import com.feature.listing.model.Article
import com.spaceflight.common.Resource

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response
import strikt.api.expectThat
import strikt.assertions.isA
import strikt.assertions.isEqualTo

class ListingRepositoryTest {
    @get:Rule
    private val testDispatcher =
        StandardTestDispatcher()

    private lateinit var listingRepositoryImpl: ListingRepositoryImpl
    private val spaceArticlesApi: SpaceArticlesApi = mockk()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        listingRepositoryImpl =
            ListingRepositoryImpl(spaceArticlesApi)
    }

    @Test
    fun `given spaceArticlesApi returns a successful response when getFeaturedArticles is invoked then it returns success`() =
        runTest {
            val fakeApiResponse = ArticleListDto(
                results = listOf(
                    ArticleDto(
                        id = 1,
                        title = "Exploring SpaceX's Latest Launch",
                        url = "https://example.com",
                        image_url = "https://example.com/image.jpg",
                        summary = "A successful mission to Mars!",
                        published_at = "2024-11-25T15:45:20.262Z",
                        updated_at = "2024-11-26",
                        featured = true,
                        launches = emptyList(),
                        events = emptyList()
                    )
                )
            )
            val expectedArticle = listOf(
                Article(
                    id = "1",
                    title = "Exploring SpaceX's Latest Launch",
                    imageUrl = "https://example.com/image.jpg",
                    publishedAt = "25 Nov 2024",
                    summary = "A successful mission to Mars!"
                )
            )
            // Act
            coEvery { spaceArticlesApi.getFeaturedArticles() } returns Response.success(
                fakeApiResponse
            )

            val result = listingRepositoryImpl.getFeaturedArticles()

            // Assert
            expectThat(result)
                .isA<Resource.Success<List<Article>>>()
                .get { data }
                .isEqualTo(expectedArticle)
        }

    @Test
    fun `given spaceArticlesApi returns successful with null data when getFeaturedArticles is invoked it return Failure`() =
        runTest {
            coEvery { spaceArticlesApi.getFeaturedArticles() } returns Response.success(null)
            val result = listingRepositoryImpl.getFeaturedArticles()
            expectThat(result)
                .isA<Resource.Failure>()
        }

    @Test
    fun `given spaceArticlesApi returns an empty list when getFeaturedArticles is invoked then it returns success with an empty list`() =
        runTest {
            coEvery { spaceArticlesApi.getFeaturedArticles() } returns Response.success(
                ArticleListDto(
                    ArrayList()
                )
            )
            val result = listingRepositoryImpl.getFeaturedArticles()

            expectThat(result)
                .isA<Resource.Success<List<Article>>>()
        }
}