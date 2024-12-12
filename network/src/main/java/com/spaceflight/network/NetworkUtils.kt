package com.spaceflight.network

import com.spaceflight.common.Resource
import com.spaceflight.network.exception.HTTPNetworkException
import com.spaceflight.network.exception.NoBodyException
import com.spaceflight.network.exception.RequestFailureException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response


/**
 * Wrap a suspending API [call] in try/catch. In case an exception is thrown, a [Resource.Failure] will wrap throwable
 */
suspend fun <T : Any, R : Any> retrofitApiCall(
    apiCall: suspend () -> Response<T>,
    mapper: (T) -> R
): Resource<R> {
    return withContext(Dispatchers.IO) {
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                if (body == null) {
                    Resource.Failure(NoBodyException())
                } else {
                    Resource.Success(mapper(body))
                }
            } else {
                Resource.Failure(RequestFailureException("${response.code()} : ${response.message()}"))
            }
        } catch (e: Exception) {
            Resource.Failure(HTTPNetworkException())
        }
    }
}