package com.example.demoapp.domain

import com.example.demoapp.localdb.model.MyInfo

interface RoomRepository {
    suspend fun insertInfo(myInfo: MyInfo):Int
}