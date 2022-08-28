package com.example.mebook.ui.presentation.full_articles

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import com.example.mebook.ui.util.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FullArticlesViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<FullArticlesAction, FullArticlesUiState>(
    FullArticlesUiState(
        type = savedStateHandle.get<String>("type")!!
    )
) {

    override fun onAction(action: FullArticlesAction) {}

    init {
        Log.i("baby", uiState.value.type)
    }

}