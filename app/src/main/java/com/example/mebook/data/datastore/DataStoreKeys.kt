package com.example.mebook.data.datastore

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.mebook.AppConstants

object DataStoreKeys {

    val USERNAME: Preferences.Key<String> by lazy {
        stringPreferencesKey(AppConstants.USERNAME_KEY)
    }

}