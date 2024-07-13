package com.example.demoapp.domain.usecase

import com.example.demoapp.domain.DemoRepository

class MockApiUseCase(private val repository: DemoRepository) {
    suspend operator fun invoke ()=repository.getMockApi()
}