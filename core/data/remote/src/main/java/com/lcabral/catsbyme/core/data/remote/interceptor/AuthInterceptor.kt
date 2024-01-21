package com.lcabral.catsbyme.core.data.remote.interceptor

import com.lcabral.catsbyme.core.data.remote.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

private const val API_KEY = "api_key"

internal class AuthInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newUrl = request.url.newBuilder()
            .addQueryParameter(API_KEY, BuildConfig.API_KEY)
            .build()
        val newRequest =request.newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(newRequest)
    }
}
