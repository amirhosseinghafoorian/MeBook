package com.example.mebook.feature.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

abstract class BaseViewModel<A, S> constructor(initialState: S) : ViewModel() {

    protected val state = MutableStateFlow(initialState)
    private val snackBarFlow = MutableSharedFlow<String>()

    val snackBarState = snackBarFlow.asSharedFlow()
    val uiState = state
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            initialState
        )

    fun submitAction(action: A) {
        onAction(action)
    }

    //
    protected abstract fun onAction(action: A)

    protected fun sendSnackBar(value: String) {
        viewModelScope.launch {
            snackBarFlow.emit(value)
        }
    }

}
