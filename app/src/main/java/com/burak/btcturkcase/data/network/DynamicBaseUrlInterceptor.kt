package com.burak.btcturkcase.data.network

import okhttp3.Interceptor
import okhttp3.Response

class DynamicBaseUrlInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        val originalHttpUrl = request.url
        val newHttpUrl = when {
            originalHttpUrl.encodedPath.contains("/history") -> {
                originalHttpUrl.newBuilder()
                    .scheme("https")
                    .host("graph-api.btcturk.com")
                    .build()
            }
            originalHttpUrl.encodedPath.contains("/currency") -> {
                originalHttpUrl.newBuilder()
                    .scheme("https")
                    .host("api.btcturk.com")
                    .build()
            }
            else -> originalHttpUrl
        }

        val newRequest = request.newBuilder().url(newHttpUrl).build()
        return chain.proceed(newRequest)
    }
}


