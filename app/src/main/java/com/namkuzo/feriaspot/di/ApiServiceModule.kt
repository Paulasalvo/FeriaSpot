package com.namkuzo.feriaspot.di

import com.namkuzo.feriaspot.BuildConfig
import com.namkuzo.feriaspot.data.network.InterceptorSpot
import com.namkuzo.feriaspot.data.network.SpotService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class ApiServiceModule {

    @Provides
    fun provideHttpClient() : OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            .addInterceptor(InterceptorSpot())
            .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY })
            .build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient) : Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(BuildConfig.SERVER_API)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    @Provides
    fun provideEmployeeService(retrofit: Retrofit): SpotService =
        retrofit.create(SpotService::class.java)

}
