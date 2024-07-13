package com.example.demoapp.domain.usecase

import androidx.room.Insert
import javax.inject.Inject

data class DemoUseCase  @Inject constructor(
            val mockApiUseCase:MockApiUseCase,
            val insertInfo:InsertInfoUseCase
)