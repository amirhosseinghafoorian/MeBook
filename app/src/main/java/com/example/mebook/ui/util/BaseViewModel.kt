package com.example.mebook.ui.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

abstract class BaseViewModel<A, S> constructor(initialState: S) : ViewModel() {

    private val _snackbarFlow = MutableSharedFlow<String>()
    val snackbarFlow = _snackbarFlow.asSharedFlow()

    protected val state = MutableStateFlow(initialState)
    val uiState = state
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            initialState
        )

    fun submitAction(action: A) {
        onAction(action)
    }

    protected abstract fun onAction(action: A)

    protected fun showSnackbar(value: String) {
        viewModelScope.launch {
            _snackbarFlow.emit(value)
        }
    }

}
