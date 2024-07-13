package com.example.demoapp.domain.usecase

import com.example.demoapp.domain.RoomRepository
import com.example.demoapp.localdb.model.MyInfo

class InsertInfoUseCase (private val roomRepository: RoomRepository) {
    suspend operator fun invoke(myInfo: MyInfo)=roomRepository.insertInfo(myInfo)
}