package com.example.demoapp.network

import com.example.demoapp.models.MockDemoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface DemoApi {
    @GET("v3/5aaecfd7-2383-4e64-8ccd-213de6d292a0")
   suspend fun getMockApiResponse():MockDemoResponse
}