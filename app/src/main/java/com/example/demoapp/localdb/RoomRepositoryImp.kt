package com.example.demoapp.localdb

import com.example.demoapp.domain.RoomRepository
import com.example.demoapp.localdb.model.MyInfo
import javax.inject.Inject

class RoomRepositoryImp @Inject constructor(val myDao: MyDao):RoomRepository {
    override suspend fun insertInfo(myInfo: MyInfo): Int {
      return myDao.insert(myInfo).toInt()
    }

}