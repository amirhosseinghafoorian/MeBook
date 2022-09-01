package com.example.mebook.ui.presentation.splash

import com.example.mebook.domain.DataStoreRepository
import com.example.mebook.ui.util.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) : BaseViewModel<SplashAction, SplashUiState>(SplashUiState()) {

    init {
        isLoggedIn()
    }

    override fun onAction(action: SplashAction) {}

    private fun isLoggedIn() {
        makeSuspendCall(
            block = {
                dataStoreRepository.isLoggedIn()
            },
            onSuccess = { result ->
                updateState {
                    copy(isLoggedIn = result)
                }
            }
        )
    }
}