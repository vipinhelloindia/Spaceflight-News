package com.feature.details.usecase

import com.feature.details.ArticleDetailRepository
import com.feature.details.ArticleDetailUseCase
import com.feature.details.model.ArticleDetail
import com.spaceflight.common.Resource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import strikt.api.expectThat
import strikt.assertions.isA
import strikt.assertions.isEqualTo

class DetailUseCaseModuleTest {

    private lateinit var articleDetailRepository: ArticleDetailRepository
    private lateinit var articleDetailUseCase: ArticleDetailUseCase

    @Before
    fun setUp() {
        // Initialize the mock repository and the use case
        articleDetailRepository = mockk()
        articleDetailUseCase = ArticleDetailUseCase(articleDetailRepository)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `given the repository returns article when getArticleDetail is invoked then it returns success with data`() =
        runTest {
            // Arrange
            val articleID = "123"
            val mockArticleDetail = ArticleDetail(
                id = 123,
                title = "Exploring SpaceX's Latest Launch",
                imageUrl = "https://example.com/image.jpg",
                publishedAt = "2024-11-25",
                summary = "A successful mission to Mars!"
            )
            coEvery { articleDetailRepository.getFeaturedArticles(articleID) } returns Resource.Success(
                mockArticleDetail
            )

            // Act
            val result = articleDetailUseCase.getArticleDetail(articleID)

            // Assert
            expectThat(result)
                .isA<Resource.Success<ArticleDetail>>()
                .get { data }
                .isEqualTo(mockArticleDetail)
        }

    @Test
    fun `given the repository returns  failure when getArticleDetail is invoked then it returns failure with  throwable`() =
        runTest {
            // Arrange
            val articleID = "123"
            val mockThrowable = Throwable("Network Error")
            coEvery { articleDetailRepository.getFeaturedArticles(articleID) } returns Resource.Failure(
                mockThrowable
            )

            // Act
            val result = articleDetailUseCase.getArticleDetail(articleID)

            // Assert
            expectThat(result)
                .isA<Resource.Failure>()
                .get { throwable }
                .isEqualTo(mockThrowable)

        }


    @Test
    fun `given the repository provides Success with Data when getArticleDetail is invoked then it returns success with article detail`() =
        runTest {
            // Arrange
            val articleID = "123"
            val mockArticle = ArticleDetail(
                id = 123,
                title = "Understanding Kotlin Coroutines",
                imageUrl = "https://example.com/article-image.jpg",
                publishedAt = "2024-11-25",
                summary = "This article dives deep into Kotlin coroutines and their usage in modern applications."
            )
            coEvery { articleDetailRepository.getFeaturedArticles(articleID) } returns Resource.Success(
                mockArticle
            )

            // Act
            val result = articleDetailUseCase.getArticleDetail(articleID)

            // Assert
            expectThat(result)
                .isA<Resource.Success<ArticleDetail>>()
                .get { data }
                .isEqualTo(mockArticle)

            coVerify(exactly = 1) { articleDetailRepository.getFeaturedArticles(articleID) }
        }
}
