package com.feature.details


import com.feature.details.data.api.SpaceArticlesDetailApi
import com.feature.details.data.repo.ArticleDetailRepositoryImpl
import com.feature.details.data.repo.model.ArticleDetailDto
import com.feature.details.data.repo.model.EventDto
import com.feature.details.data.repo.model.LaunchDto
import com.feature.details.model.ArticleDetail
import com.spaceflight.common.Resource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response
import strikt.api.expectThat
import strikt.assertions.isA
import strikt.assertions.isEqualTo


class ArticleDetailRepositoryImplTest {


    @get:Rule
    private val testDispatcher =
        StandardTestDispatcher() // For testing coroutines with the Main dispatcher.

    private val mockApi = mockk<SpaceArticlesDetailApi>()
    private lateinit var repository: ArticleDetailRepositoryImpl

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = ArticleDetailRepositoryImpl(mockApi, testDispatcher)
    }

    @Test
    fun `given the API call is successful when getFeaturedArticles is invoked then it returns success with article detail`() =
        runTest {
            // Arrange
            val articleId = "123"
            val fakeApiResponse = ArticleDetailDto(
                id = 123,
                title = "SpaceX Achieves Milestone",
                url = "https://example.com/article",
                image_url = "https://example.com/image.jpg",
                news_site = "Space News",
                summary = "SpaceX's latest rocket launch was a success.",
                published_at = "2024-11-25",
                updated_at = "2024-11-26",
                featured = true,
                launches = listOf(
                    LaunchDto(
                        launch_id = "launch1",
                        provider = "SpaceX"
                    )
                ),
                events = listOf(
                    EventDto(
                        event_id = 1,
                        provider = "NASA"
                    )
                )
            )
            val expectedArticleDetail = ArticleDetail(
                id = 123,
                title = "SpaceX Achieves Milestone",
                imageUrl = "https://example.com/image.jpg",
                publishedAt = "2024-11-25",
                summary = "SpaceX's latest rocket launch was a success."
            )

            coEvery { mockApi.getArticleById(articleId) }.returns(Response.success(fakeApiResponse))

            // Act
            val result = repository.getFeaturedArticles(articleId)

            // Assert
            expectThat(result)
                .isA<Resource.Success<ArticleDetail>>()
                .get { data }
                .isEqualTo(expectedArticleDetail)
        }

    @Test
    fun `given the API call fails when getFeaturedArticles is invoked then it returns failure`() =
        runTest {
            // Arrange
            val articleId = "123"
            val errorBody = ResponseBody.create(
                "application/json".toMediaTypeOrNull(), ""
            )
            coEvery { mockApi.getArticleById(articleId) }.returns(Response.error(404, errorBody))

            // Act
            val result = repository.getFeaturedArticles(articleId)

            // Assert
            expectThat(result)
                .isA<Resource.Failure>()
        }
}
