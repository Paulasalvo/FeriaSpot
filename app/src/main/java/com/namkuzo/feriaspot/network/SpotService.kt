package com.namkuzo.feriaspot.network

import android.util.Log
import com.namkuzo.feriaspot.BuildConfig
import com.namkuzo.feriaspot.data.SpotDTO
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface SpotService {
    @GET("allSpots")
    suspend fun getAllSpots(
        @Query("comuna") comuna: String? = null
    ) : List<SpotDTO>
}

fun getService() : SpotService {
    return getClient()
        .create(SpotService::class.java)
}

fun getClient() : Retrofit {
    val retrofit = Retrofit.Builder()

    val client = OkHttpClient()
        .newBuilder()
        .addInterceptor(InterceptorSpot())
        .build()

    return retrofit
        .client(client)
        //.baseUrl(BuildConfig.LOCAL_API)
        .baseUrl(BuildConfig.SERVER_API)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
}

class InterceptorSpot() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("ApiKey", BuildConfig.API_KEY)
            .build()

        Log.i("Interceptor", request.headers.toString())
        return chain.proceed(request)
    }

}