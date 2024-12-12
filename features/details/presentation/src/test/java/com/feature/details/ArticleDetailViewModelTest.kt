package com.feature.details

import app.cash.turbine.test
import com.feature.details.model.ArticleDetail
import com.feature.details.mvi.ArticleDetailIntent
import com.feature.details.mvi.DetailScreenState
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
import strikt.api.expectThat
import strikt.assertions.isA
import strikt.assertions.isEqualTo

class ArticleDetailViewModelTest {

    @get:Rule
    private val testDispatcher =
        StandardTestDispatcher() // For testing coroutines with the Main dispatcher.

    private val detailUseCase = mockk<ArticleDetailUseCase>()
    private lateinit var viewModel: ArticleDetailViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = ArticleDetailViewModel(detailUseCase)
    }

    @Test
    fun `given ViewModel initialization when no actions are performed then state is InitialState`() =
        runTest {
            // Assert that the initial state is InitialState
            expectThat(viewModel.viewState.value)
                .isA<DetailScreenState.InitialState>()
        }

    @Test
    fun `given successful response from use case when fetching completes then state is Success`() =
        runTest {
            val articleId = 1
            val mockDetail = ArticleDetail(
                id = articleId,
                title = "Space Exploration Milestone",
                imageUrl = "https://example.com/article.jpg",
                publishedAt = "2024-11-25",
                summary = "A detailed summary of the space exploration milestone."
            )
            coEvery { detailUseCase.getArticleDetail(any()) } returns Resource.Success(mockDetail)

            viewModel.handleIntent(ArticleDetailIntent.FetchArticleDetail("article-id"))

            viewModel.viewState.test {
                expectThat(awaitItem())
                    .isA<DetailScreenState.Loading>()
                expectThat(awaitItem())
                    .isA<DetailScreenState.Success>()
                    .and {
                        get { detail }.isEqualTo(mockDetail)
                    }

                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `given failure response from use case when fetching fails then state is Error`() = runTest {
        // Mock failure response
        val message = "Network error occurred"
        coEvery { detailUseCase.getArticleDetail(any()) } returns Resource.Failure(
            Throwable(
                message
            )
        )

        viewModel.handleIntent(ArticleDetailIntent.FetchArticleDetail("1"))

        viewModel.viewState.test {
            expectThat(awaitItem())
                .isA<DetailScreenState.Loading>()
            expectThat(awaitItem())
                .isA<DetailScreenState.Error>()
                .and {
                    get { errorMessage }.isEqualTo(message)
                }

            cancelAndIgnoreRemainingEvents()
        }
    }
}
