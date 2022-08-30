package com.example.mebook.ui.presentation.authenticate

import androidx.lifecycle.viewModelScope
import com.example.mebook.domain.TestRepository
import com.example.mebook.ui.presentation.authenticate.AuthenticateAction.CallApi
import com.example.mebook.ui.presentation.authenticate.AuthenticateAction.CallDatabase
import com.example.mebook.ui.util.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticateViewModel @Inject constructor(
    private val repo: TestRepository
) : BaseViewModel<AuthenticateAction, AuthenticateUiState>(AuthenticateUiState()) {

    init {
        getUsers()
    }

    override fun onAction(action: AuthenticateAction) {
        when (action) {
            is CallApi -> testApiCall()
            is CallDatabase -> addUser()
            else -> throw IllegalArgumentException("unSupported action : $action")
        }
    }

    private fun testApiCall() {
        makeSuspendCall(
            block = {
                repo.getTestData()
            },
            onSuccess = { result ->
                updateState { copy(name = "name from api : ${result.name}") }
            },
            onLoading = { isLoading ->
                updateState { copy(isLoading = isLoading) }
            }
        )
    }

    private fun addUser() {
        makeSuspendCall(
            block = { repo.fetchUser() }
        )
    }

    private fun getUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getAllUsers().collect { result ->
                if (result.isNotEmpty()) {
                    updateState { copy(name = "user : ${result[0].name}") }
                } else {
                    updateState { copy(name = "no users available") }
                }
            }
        }
    }

}