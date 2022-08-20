package com.example.mebook.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mebook.model.database.ArticleEntity
import com.example.mebook.model.database.FeaturedEntity
import com.example.mebook.model.database.FeedEntity
import com.example.mebook.model.database.User

@Database(
    entities = [User::class, FeedEntity::class,FeaturedEntity::class, ArticleEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MeBookDatabase : RoomDatabase() {
    abstract fun testDao(): TestDao
    abstract fun articleDao(): ArticleDao
}