package com.example.mebook.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mebook.model.database.User

@Database(
    entities = [User::class],
    version = 1,
    exportSchema = false
)
abstract class MeBookDatabase : RoomDatabase() {
    abstract fun testDao(): TestDao
}