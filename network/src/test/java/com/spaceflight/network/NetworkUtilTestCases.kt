package com.spaceflight.network

import com.spaceflight.network.exception.NoBodyException
import com.spaceflight.network.exception.RequestFailureException
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody
import org.junit.Test
import retrofit2.Response
import strikt.api.expectThat
import strikt.assertions.isA
import strikt.assertions.isEqualTo

class NetworkUtilTestCases {

    private val transformMock: (Int) -> String = { it.toString() }

    @Test
    fun `given successful API call with valid response when safeApiCall is invoked then it returns Resource_Success with transformed data`() =
        runTest {
            // Arrange
            val mockApiCall = mockk<suspend () -> Response<Int>>()
            coEvery { mockApiCall() } returns Response.success(42)

            // Act
            val result = retrofitApiCall(mockApiCall, transformMock)

            // Assert
            expectThat(result)
                .isA<com.spaceflight.common.Resource.Success<String>>()
                .get { data }
                .isEqualTo("42")
        }

    @Test
    fun `given unsuccessful API response when safeApiCall is invoked then it returns Resource_Failure with RequestFailureException`() =
        runTest {
            // Arrange
            val mockApiCall = mockk<suspend () -> Response<Int>>()
            coEvery { mockApiCall() } returns Response.error(
                404,
                ResponseBody.create(null, "Error")
            )

            val result = retrofitApiCall(mockApiCall, transformMock)

            expectThat(result)
                .isA<com.spaceflight.common.Resource.Failure>()
                .and {
                    get { throwable }
                        .isA<RequestFailureException>()
                        .get { message }
                        .isEqualTo("404 : Response.error()")
                }
        }

    @Test
    fun `given exception is thrown during API call when safeApiCall is invoked then it returns Resource_Failure with  exception`() =
        runTest {
            // Arrange
            val mockApiCall = mockk<suspend () -> Response<Int>>()
            coEvery { mockApiCall() } throws IllegalArgumentException("Invalid argument")

            // Act
            val result = retrofitApiCall(mockApiCall, transformMock)

            // Assert
            expectThat(result)
                .isA<com.spaceflight.common.Resource.Failure>()
                .and {
                    get { throwable }
                        .isA<IllegalArgumentException>()
                        .get { message }
                        .isEqualTo("Invalid argument")
                }
        }

    @Test
    fun `given API response body is null when safeApiCall is invoked then it returns Resource_Failure with NoBodyException`() =
        runTest {
            val mockApiCall = mockk<suspend () -> Response<Int>>()
            coEvery { mockApiCall() } returns Response.success(null)

            val result = retrofitApiCall(mockApiCall, transformMock)

            expectThat(result)
                .isA<com.spaceflight.common.Resource.Failure>()
                .and {
                    get { throwable }
                        .isA<NoBodyException>()
                }
        }
}