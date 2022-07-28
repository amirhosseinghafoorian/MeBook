package com.example.mebook.di

import com.example.mebook.data.repository.TestRepositoryImpl
import com.example.mebook.domain.TestRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideTestRepository(testRepositoryImpl: TestRepositoryImpl): TestRepository

}