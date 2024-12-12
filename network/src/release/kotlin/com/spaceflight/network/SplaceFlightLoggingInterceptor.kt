package com.spaceflight.network

import okhttp3.Interceptor
import okhttp3.Response

class SplaceFlightLoggingInterceptor @JvmOverloads constructor(

) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        return chain.proceed(request)
    }
}