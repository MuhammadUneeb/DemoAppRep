package com.example.demoapp.data

import com.example.demoapp.domain.DemoRepository
import com.example.demoapp.models.MockDemoResponse
import com.example.demoapp.network.DemoApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DemoRepositoryImp @Inject constructor(
    private val demoApi: DemoApi
):DemoRepository {
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
    override suspend fun getMockApi(): Flow<MockDemoResponse> = flow<MockDemoResponse> {
       emit( demoApi.getMockApiResponse())
    }.flowOn(ioDispatcher)


}