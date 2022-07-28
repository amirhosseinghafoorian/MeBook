package com.example.mebook.ui.presentation.authenticate

import androidx.lifecycle.viewModelScope
import com.example.mebook.domain.TestRepository
import com.example.mebook.ui.presentation.authenticate.AuthenticateAction.ChangeName
import com.example.mebook.ui.presentation.authenticate.AuthenticateAction.SnackBar
import com.example.mebook.ui.util.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AuthenticateViewModel @Inject constructor(
    private val repo: TestRepository
) : BaseViewModel<AuthenticateAction, AuthenticateUiState>(AuthenticateUiState()) {

    override fun onAction(action: AuthenticateAction) {
        when (action) {
            is SnackBar -> testApiCall()
            is ChangeName -> {
                state.update {
                    it.copy(name = action.name)
                }
            }
            else -> throw IllegalArgumentException("unknown action : $action")
        }
    }

    private fun testApiCall() {
        viewModelScope.launch {
            val result = repo.getTestData()
            showSnackbar(result.count.toString())
        }
    }

}
