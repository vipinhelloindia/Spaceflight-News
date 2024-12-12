package com.spaceflight.network.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.spaceflight.network.API_ENDPOINT
import com.spaceflight.network.SplaceFlightLoggingInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Singleton


private const val CONTENT_TYPE = "application/json"


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {


    @Provides
    fun loggingInterceptor() = SplaceFlightLoggingInterceptor();

    @Singleton
    @Provides
    fun provideRetrofit(
        client: OkHttpClient,
        converter: Converter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(converter)
            .baseUrl(API_ENDPOINT)
            .build();
    }

    @Provides
    fun provideOkHttpClient(loggingInterceptor: SplaceFlightLoggingInterceptor): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
        clientBuilder.addInterceptor(loggingInterceptor)
        return clientBuilder.build()
    }

    @Provides
    fun provideJsonConvertor(): Converter.Factory {
        val retroJson = Json { ignoreUnknownKeys = true }
        val contentType = CONTENT_TYPE.toMediaType()
        return retroJson.asConverterFactory(contentType)
    }


}