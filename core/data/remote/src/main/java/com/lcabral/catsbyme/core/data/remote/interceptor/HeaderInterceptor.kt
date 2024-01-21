package com.lcabral.catsbyme.core.data.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor {
    internal class HeaderInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request().newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("Connection", "close")
                .removeHeader("Content-Length")
                .build()
            return chain.proceed(request)
        }
    }
}
