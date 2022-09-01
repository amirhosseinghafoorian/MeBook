package com.example.mebook.ui.presentation.article

import androidx.lifecycle.SavedStateHandle
import com.example.mebook.ui.util.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) :
    BaseViewModel<ArticleAction, ArticleUiState>(ArticleUiState()) {

    override fun onAction(action: ArticleAction) {}

    init {
        getArticleDetail(savedStateHandle.get<Int>("articleId")!!)
    }

    private fun getArticleDetail(articleId: Int) {
        // todo call checkIsOwn onSuccess
    }

    private fun checkIsOwnArticle() {
        // todo check authorUsername with savedUsername
    }
}