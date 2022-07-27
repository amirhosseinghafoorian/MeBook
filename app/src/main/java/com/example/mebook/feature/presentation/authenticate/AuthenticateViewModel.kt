package com.example.mebook.feature.presentation.authenticate

import com.example.mebook.feature.presentation.authenticate.AuthenticateAction.ChangeName
import com.example.mebook.feature.presentation.authenticate.AuthenticateAction.SnackBar
import com.example.mebook.feature.util.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import java.lang.IllegalArgumentException
import javax.inject.Inject


@HiltViewModel
class AuthenticateViewModel @Inject constructor() :
    BaseViewModel<AuthenticateAction, AuthenticateUiState>(AuthenticateUiState()) {

    override fun onAction(action: AuthenticateAction) {
        when (action) {
            is SnackBar -> sendSnackbar(action.message)
            is ChangeName -> {
                state.update {
                    it.copy(name = action.name)
                }
            }
            else -> throw IllegalArgumentException("unknown action : $action")
        }
    }

}
