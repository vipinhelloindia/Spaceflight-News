package com.feature.listing


import com.feature.listing.model.Article
import com.spaceflight.common.Resource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import strikt.api.expectThat
import strikt.assertions.isA
import strikt.assertions.isEqualTo

class ListingUseCaseTest {
    private lateinit var listingUseCase: ListingUseCase
    private val listingRepository: ListingRepository = mockk()

    @Before
    fun setUp() {
        listingUseCase = ListingUseCase(listingRepository)
    }

    @Test
    fun `given listingRepository returns success when getFeaturedArticles is invoked then it returns Resource_Success`() =
        runTest {
            // Arrange
            val mockArticles = listOf(
                Article("1", "Title 1", "url1", "2024-11-25", "Summary 1"),
                Article("2", "Title 2", "url2", "2024-11-26", "Summary 2")
            )

            coEvery {
                listingRepository.getFeaturedArticles(
                    any(),
                    any(),
                    any(),
                    any()
                )
            } returns Resource.Success(mockArticles)

            // Act
            val result = listingUseCase.getFeaturedArticles(2)

            // Assert
            expectThat(result).isA<Resource.Success<List<Article>>>().get { data }
                .isEqualTo(mockArticles)
        }

    @Test
    fun `given listingRepository returns failure when getFeaturedArticles is invoked then it returns Resource_Failure with throwable`() =
        runTest {
            // Arrange
            val mockThrowable = Throwable("Network Error")
            coEvery {
                listingRepository.getFeaturedArticles(
                    any(),
                    any(),
                    any(),
                    any()
                )
            } returns Resource.Failure(mockThrowable)

            // Act
            val result = listingUseCase.getFeaturedArticles(2)

            // Assert
            expectThat(result).isA<Resource.Failure>().get { throwable }.isEqualTo(mockThrowable)

        }

    @Test
    fun `given listingRepository returns loading when getFeaturedArticles is invoked then it returns Resource_Loading`() =
        runTest {
            // Arrange
            coEvery {
                listingRepository.getFeaturedArticles(
                    any(),
                    any(),
                    any(),
                    any()
                )
            } returns Resource.Loading

            // Act
            val result = listingUseCase.getFeaturedArticles(2)

            // Assert
            expectThat(result).isA<Resource.Loading>()

        }

    @Test
    fun `given listingRepository returns data when getFeaturedArticles is invoked then it returns success with the data`() =
        runTest {
            coEvery {
                listingRepository.getFeaturedArticles(
                    any(),
                    any(),
                    any(),
                    any()
                )
            } returns Resource.Success(listOf())

            val result = listingUseCase.getFeaturedArticles(20)

            expectThat(result).isA<Resource.Success<List<Article>>>()
        }
}