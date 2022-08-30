package com.example.mebook.ui.presentation.profile

import androidx.lifecycle.SavedStateHandle
import com.example.mebook.domain.DataStoreRepository
import com.example.mebook.ui.presentation.profile.ProfileAction.Logout
import com.example.mebook.ui.util.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val dataStoreRepository: DataStoreRepository
) :
    BaseViewModel<ProfileAction, ProfileUiState>(ProfileUiState()) {

    override fun onAction(action: ProfileAction) {
        when (action) {
            Logout -> logoutUser()
            else -> throw IllegalArgumentException("unSupported action : $action")
        }
    }

    init {
        verifyUsername(savedStateHandle.get<String>("username"))
    }

    private fun logoutUser() {
        makeSuspendCall(
            block = {
                dataStoreRepository.setLogout()
            },
            onSuccess = {
                updateState { copy(logout = true) }
            }
        )
    }

    private fun verifyUsername(username: String?) {
        makeSuspendCall(
            block = {
                dataStoreRepository.getUsername()
            },
            onSuccess = { savedUsername ->
                if (username == null || username == savedUsername) {
                    setUsername(isOwnProfile = true, username = savedUsername)
                } else {
                    setUsername(isOwnProfile = false, username = username)
                    isFollowingUser(username)
                }
            }
        )
    }

    private fun isFollowingUser(username: String) {
        makeSuspendCall(
            block = {
                // todo call isFollowing user api
            },
            onSuccess = {
                val result = true
                updateState { copy(isFollowingUser = result) }
            }
        )
    }

    private fun setUsername(
        isOwnProfile: Boolean,
        username: String
    ) {
        updateState { copy(isOwnProfile = isOwnProfile, username = username) }
    }

}