package com.example.mebook.ui.presentation.profile

import androidx.lifecycle.SavedStateHandle
import com.example.mebook.domain.DataStoreRepository
import com.example.mebook.domain.RemoteRepository
import com.example.mebook.ui.presentation.profile.ProfileAction.Logout
import com.example.mebook.ui.presentation.profile.ProfileAction.ToggleFollowState
import com.example.mebook.ui.util.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val dataStoreRepository: DataStoreRepository,
    private val remoteRepository: RemoteRepository
) :
    BaseViewModel<ProfileAction, ProfileUiState>(ProfileUiState()) {

    override fun onAction(action: ProfileAction) {
        when (action) {
            Logout -> logoutUser()
            is ToggleFollowState -> toggleFollowUser(action.isFollowing)
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
                remoteRepository.isFollowing(username)
            },
            onSuccess = { result ->
                updateState { copy(isFollowingUser = result) }
            }
        )
    }

    private fun toggleFollowUser(isFollowing: Boolean) {
        if (isFollowing) {
            makeSuspendCall(
                block = {
                    remoteRepository.unFollowUser(uiState.value.username!!)
                },
                onSuccess = {
                    updateState { copy(isFollowingUser = false) }
                }
            )
        } else {
            makeSuspendCall(
                block = {
                    remoteRepository.followUser(uiState.value.username!!)
                },
                onSuccess = {
                    updateState { copy(isFollowingUser = true) }
                }
            )
        }
        updateFeed()
    }

    private fun setUsername(
        isOwnProfile: Boolean,
        username: String
    ) {
        updateState { copy(isOwnProfile = isOwnProfile, username = username) }
    }

    private fun updateFeed() {
        makeSuspendCall(
            block = {
                remoteRepository.updateFeed()
            }
        )
    }

}