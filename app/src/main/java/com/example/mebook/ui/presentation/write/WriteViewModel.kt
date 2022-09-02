package com.example.mebook.ui.presentation.write

import com.example.mebook.ui.presentation.write.WriteAction.Publish
import com.example.mebook.ui.util.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WriteViewModel @Inject constructor() :
    BaseViewModel<WriteAction, WriteUiState>(WriteUiState()) {

    override fun onAction(action: WriteAction) {
        when (action) {
            Publish -> publishArticle()
            else -> throw IllegalArgumentException("unSupported action : $action")
        }
    }

    private fun publishArticle() {
        // todo publish article
        //  logout with its result through isPublished
    }

}