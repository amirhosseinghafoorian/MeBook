package com.example.mebook.ui.presentation.search

import com.example.mebook.domain.RemoteRepository
import com.example.mebook.ui.presentation.search.SearchAction.UpdateTextField
import com.example.mebook.ui.util.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val remoteRepository: RemoteRepository
) :
    BaseViewModel<SearchAction, SearchUiState>(SearchUiState()) {

    private var searchJob: Job? = null

    override fun onAction(action: SearchAction) {
        when (action) {
            is UpdateTextField -> searchUser(action.value)
        }
    }

    private fun searchUser(value: String) {
        uiState.value.searchState.onChanged(value)
        searchJob?.cancel()

        if (value.isNotEmpty()) {
            makeSuspendCall(
                block = {
                    remoteRepository.searchUsers(value)
                },
                onSuccess = { result ->
                    updateState { copy(searchUsers = result) }
                },
                suspendJob = {
                    searchJob = it
                }
            )
        } else {
            updateState { copy(searchUsers = listOf()) }
        }
    }
}