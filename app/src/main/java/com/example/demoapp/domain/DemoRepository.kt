package com.example.demoapp.domain

import com.example.demoapp.models.MockDemoResponse
import kotlinx.coroutines.flow.Flow

interface DemoRepository {
    suspend fun getMockApi():Flow<MockDemoResponse>
}