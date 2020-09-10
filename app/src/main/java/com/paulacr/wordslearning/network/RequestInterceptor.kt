package com.paulacr.wordslearning.network

import okhttp3.Interceptor
import okhttp3.Response

const val AUTH_KEY = "Ocp-Apim-Subscription-Key"
const val API_VERSION = "api-version"

class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val originalRequest = chain.request()
        val requestBuilder = originalRequest.newBuilder()
            .addHeader(
                AUTH_KEY, "1a55a4d4ef5045bc928a81c672bafed6"
            ).addHeader(
                API_VERSION, "3.0"
            ).build()

        return chain.proceed(requestBuilder)
    }
}
