package com.example.demoapp.network

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class InterceptorData @Inject constructor():Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request=chain.request().newBuilder()
        return chain.proceed(request.build())
    }
}