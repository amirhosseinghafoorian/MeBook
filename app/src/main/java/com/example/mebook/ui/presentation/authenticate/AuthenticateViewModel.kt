package com.example.mebook.ui.presentation.authenticate

import com.example.mebook.ui.presentation.authenticate.AuthenticateAction.ChangeName
import com.example.mebook.ui.presentation.authenticate.AuthenticateAction.SnackBar
import com.example.mebook.ui.util.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class AuthenticateViewModel @Inject constructor() :
    BaseViewModel<AuthenticateAction, AuthenticateUiState>(AuthenticateUiState()) {

    override fun onAction(action: AuthenticateAction) {
        when (action) {
            is SnackBar -> showSnackbar(action.message)
            is ChangeName -> {
                state.update {
                    it.copy(name = action.name)
                }
            }
            else -> throw IllegalArgumentException("unknown action : $action")
        }
    }

}
