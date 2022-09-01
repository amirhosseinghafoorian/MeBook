package com.example.mebook.ui.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mebook.ui.util.Constants.INTERNET_ERROR
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

abstract class BaseViewModel<A, S> constructor(initialState: S) : ViewModel() {

    private val _snackbarFlow = MutableSharedFlow<String>()
    val snackbarFlow = _snackbarFlow.asSharedFlow()

    private val state = MutableStateFlow(initialState)
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

    private suspend fun <R> runCatching(block: suspend () -> R): Result<R> {
        return try {
            Result.success(block())
        } catch (e: Throwable) {
            Result.failure(e)
        }
    }

    private suspend fun <R> makeSuspendCall(block: suspend () -> R): Result<R> {
        return withContext(Dispatchers.IO) {
            runCatching(block)
        }
    }

    protected fun <R> makeSuspendCall(
        block: suspend () -> R,
        onSuccess: ((R) -> Unit)? = null,
        onError: ((Exception) -> Unit)? = null,
        onLoading: ((Boolean) -> Unit)? = null,
        suspendJob: ((Job) -> Unit)? = null,
        showSnackbarOnError: Boolean = true
    ) {
        viewModelScope.launch {

            onLoading?.invoke(true)
            makeSuspendCall(block).apply {
                try {
                    val result = getOrThrow()
                    onSuccess?.invoke(result)
                } catch (e: IOException) {
                    if (showSnackbarOnError) showSnackbar(INTERNET_ERROR)
                    onError?.invoke(Exception(INTERNET_ERROR, e.cause))
                    e.printStackTrace()
                } catch (e: Exception) {
                    if (showSnackbarOnError) {
                        e.message?.let { errorMessage ->
                            showSnackbar(errorMessage)
                        }
                    }
                    onError?.invoke(e)
                    e.printStackTrace()
                } finally {
                    onLoading?.invoke(false)
                }
            }
        }.apply {
            suspendJob?.invoke(this)
        }
    }

    protected fun updateState(newState: S.(S) -> S) {
        state.apply {
            update { value.newState(it) }
        }
    }

    // todo effect should be added
    //  snackbarObserver LaunchedEffect should be updated
}
