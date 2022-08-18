package com.example.mebook.ui.presentation.profile

import com.example.mebook.domain.DataStoreRepository
import com.example.mebook.ui.util.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) :
    BaseViewModel<ProfileAction, ProfileUiState>(ProfileUiState()) {

    override fun onAction(action: ProfileAction) {
        when (action) {
            ProfileAction.Logout -> logoutUser()
        }
    }

    private fun logoutUser() {
        makeSuspendCall(
            block = {
                dataStoreRepository.setLogout()
            }
        )
    }

}