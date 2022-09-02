package com.example.mebook.ui.presentation.write

import com.example.mebook.domain.RemoteRepository
import com.example.mebook.ui.presentation.write.WriteAction.Publish
import com.example.mebook.ui.util.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WriteViewModel @Inject constructor(
    private val remoteRepository: RemoteRepository
) :
    BaseViewModel<WriteAction, WriteUiState>(WriteUiState()) {

    override fun onAction(action: WriteAction) {
        when (action) {
            is Publish -> publishArticle(action.title, action.body)
            else -> throw IllegalArgumentException("unSupported action : $action")
        }
    }

    private fun publishArticle(title: String, body: String) {
        makeSuspendCall(
            block = {
                remoteRepository.publishArticle(title, body)
            },
            onSuccess = {
                updateState { copy(isPublished = true) }
            }
        )
    }

}