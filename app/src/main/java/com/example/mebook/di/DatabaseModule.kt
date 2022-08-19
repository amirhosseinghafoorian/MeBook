package com.example.mebook.di

import android.content.Context
import androidx.room.Room
import com.example.mebook.data.database.ArticleDao
import com.example.mebook.data.database.MeBookDatabase
import com.example.mebook.data.database.TestDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideMeBookDb(
        @ApplicationContext context: Context
    ): MeBookDatabase = Room
        .databaseBuilder(
            context,
            MeBookDatabase::class.java,
            "MeBookDb.db"
        )
        .build()

    @Provides
    @Singleton
    fun provideTestDao(meBookDb: MeBookDatabase): TestDao {
        return meBookDb.testDao()
    }

    @Provides
    @Singleton
    fun provideArticleDao(meBookDb: MeBookDatabase): ArticleDao {
        return meBookDb.articleDao()
    }
}