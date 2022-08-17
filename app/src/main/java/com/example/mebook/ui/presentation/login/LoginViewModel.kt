package com.example.mebook.ui.presentation.login

import com.example.mebook.domain.DataStoreRepository
import com.example.mebook.ui.presentation.login.LoginAction.ConfirmLogin
import com.example.mebook.ui.util.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) :
    BaseViewModel<LoginAction, LoginUiState>(LoginUiState()) {

    override fun onAction(action: LoginAction) {
        when (action) {
            is ConfirmLogin -> loginUser(action.username, action.password)
        }
    }

    private fun loginUser(username: String, password: String) {
        // todo call login api
        //  with the result of it call function below
        makeSuspendCall(
            block = {
                dataStoreRepository.setLogin(username)
            }
        )
    }

}