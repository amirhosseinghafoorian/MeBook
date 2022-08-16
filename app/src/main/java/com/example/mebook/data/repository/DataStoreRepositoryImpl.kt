package com.example.mebook.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.mebook.data.datastore.DataStoreKeys
import com.example.mebook.domain.DataStoreRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class DataStoreRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : DataStoreRepository {

    override suspend fun isLoggedIn(): Boolean =
        dataStore.data.first()[DataStoreKeys.USERNAME] != null

}