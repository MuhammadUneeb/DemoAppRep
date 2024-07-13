package com.example.demoapp.localdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.demoapp.localdb.model.MyInfo


@Database(entities = [MyInfo::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun myDao(): MyDao
}