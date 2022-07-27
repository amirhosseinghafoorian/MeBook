package com.example.mebook.feature.presentation.authenticate

import com.example.mebook.feature.util.BaseViewModel
import com.example.mebook.feature.util.Test
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class AuthenticateViewModel @Inject constructor() : BaseViewModel<Test, Test>(Test()) {

    override fun onAction(action: Test) {
        state.update {
            it
        }
    }

// todo rotation should be tested
// todo snackBar should be tested
// todo UiState should be tested
// todo add application to manifest

}
