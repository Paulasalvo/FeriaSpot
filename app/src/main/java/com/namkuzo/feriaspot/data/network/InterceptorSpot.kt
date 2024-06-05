package com.namkuzo.feriaspot.data.network

import android.util.Log
import com.namkuzo.feriaspot.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class InterceptorSpot() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("ApiKey", BuildConfig.API_KEY)
            .build()

        Log.i("Interceptor", request.headers.toString())
        return chain.proceed(request)
    }

}
