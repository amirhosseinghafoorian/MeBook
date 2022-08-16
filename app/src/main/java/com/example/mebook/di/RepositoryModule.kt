package com.example.mebook.di

import com.example.mebook.data.repository.DataStoreRepositoryImpl
import com.example.mebook.data.repository.LocalRepositoryImpl
import com.example.mebook.data.repository.RemoteRepositoryImpl
import com.example.mebook.data.repository.TestRepositoryImpl
import com.example.mebook.domain.DataStoreRepository
import com.example.mebook.domain.LocalRepository
import com.example.mebook.domain.RemoteRepository
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

    @Binds
    @Singleton
    abstract fun provideLocalRepository(localRepositoryImpl: LocalRepositoryImpl): LocalRepository

    @Binds
    @Singleton
    abstract fun provideRemoteRepository(remoteRepositoryImpl: RemoteRepositoryImpl): RemoteRepository

    @Binds
    @Singleton
    abstract fun provideDataStoreRepository(dataStoreRepositoryImpl: DataStoreRepositoryImpl): DataStoreRepository

}