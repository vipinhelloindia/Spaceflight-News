package com.feature.listing

import app.cash.turbine.test
import com.feature.listing.model.Article
import com.feature.listing.mvi.FetchSpaceNewsIntent
import com.feature.listing.mvi.ListingScreenState
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

class ListingViewModelTest {

    @get:Rule
    private val testDispatcher =
        StandardTestDispatcher() // For testing coroutines with the Main dispatcher.

    private val listingUseCase = mockk<ListingUseCase>()
    private lateinit var viewModel: ListingViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = ListingViewModel(listingUseCase)

    }

    @Test
    fun `given ViewModel initialization when no actions are performed then state is InitialState`() =
        runTest {
            expectThat(viewModel.viewState.value)
                .isA<ListingScreenState.InitialState>()

        }

    @Test
    fun `given successful response from use case when fetchSpaceNews is triggered then state is Success`() =
        runTest {
            val mockArticles = listOf(
                Article(
                    id = "1",
                    title = "SpaceX Launch Successful",
                    imageUrl = "https://example.com/image1.jpg",
                    publishedAt = "2024-11-25",
                    summary = "SpaceX successfully launched its latest rocket."
                ), Article(
                    id = "2",
                    title = "NASA's New Discovery",
                    imageUrl = "https://example.com/image2.jpg",
                    publishedAt = "2024-11-24",
                    summary = "NASA discovered a new planet with potential for life."
                )
            )
            coEvery { listingUseCase.getFeaturedArticles(20) } returns Resource.Success(mockArticles)
            viewModel.handleIntent(FetchSpaceNewsIntent.FetchSpaceNews)

            viewModel.viewState.test {
                expectThat(awaitItem())
                    .isA<ListingScreenState.Loading>()
                expectThat(awaitItem())
                    .isA<ListingScreenState.Success>()
                    .and {
                        get { users }.isEqualTo(mockArticles)
                    }
            }
        }

    @Test
    fun `given failure response from use case when fetchSpaceNews is triggered then state is Error`() =
        runTest {
            // Mock failure response
            val errorMessage = "Network error occurred"
            coEvery { listingUseCase.getFeaturedArticles(20) } returns Resource.Failure(
                Throwable(
                    errorMessage
                )
            )

            viewModel.handleIntent(FetchSpaceNewsIntent.FetchSpaceNews)
            viewModel.viewState.test {
                expectThat(awaitItem())
                    .isA<ListingScreenState.Loading>()
                expectThat(awaitItem())
                    .isA<ListingScreenState.Error>()
                    .and {
                        get { message }.isEqualTo(errorMessage)
                    }
            }
        }
}
