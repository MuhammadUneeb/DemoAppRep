package com.example.demoapp

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.demoapp.localdb.AppDatabase
import com.example.demoapp.localdb.MyDao
import com.example.demoapp.localdb.model.MyInfo
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

class InsertDataRoom {
    private lateinit var database: AppDatabase
    private lateinit var myDao: MyDao

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        myDao = database.myDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertMyInfo() = runTest {
        // Given a User
        val user = MyInfo(email = "test@gmail.com", password = "123456")
        myDao.insert(user)
    }
}