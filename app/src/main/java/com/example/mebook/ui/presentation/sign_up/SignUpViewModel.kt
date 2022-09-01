package com.example.mebook.ui.presentation.sign_up

import com.example.mebook.domain.DataStoreRepository
import com.example.mebook.domain.RemoteRepository
import com.example.mebook.ui.presentation.sign_up.SignUpAction.ConfirmSignUp
import com.example.mebook.ui.util.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository,
    private val remoteRepository: RemoteRepository,
) : BaseViewModel<SignUpAction, SignUpUiState>(SignUpUiState()) {

    override fun onAction(action: SignUpAction) {
        when (action) {
            is ConfirmSignUp -> signUpUser(action.username, action.password)
        }
    }

    private fun signUpUser(username: String, password: String) {
        makeSuspendCall(
            block = { remoteRepository.signUpUser(username, password) },
            onSuccess = { setLogin(username) },
            onLoading = { value ->
                updateState { copy(isLoading = value) }
            }
        )
    }

    private fun setLogin(username: String) {
        makeSuspendCall(
            block = {
                dataStoreRepository.setLogin(username)
            },
            onSuccess = {
                updateState { copy(isLoginSuccess = true) }
            }
        )
    }
}