package com.example.mebook.ui.presentation.article

import androidx.lifecycle.SavedStateHandle
import com.example.mebook.domain.DataStoreRepository
import com.example.mebook.domain.RemoteRepository
import com.example.mebook.ui.presentation.article.ArticleAction.DeleteArticle
import com.example.mebook.ui.util.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val remoteRepository: RemoteRepository,
    private val dataStoreRepository: DataStoreRepository
) :
    BaseViewModel<ArticleAction, ArticleUiState>(ArticleUiState()) {

    override fun onAction(action: ArticleAction) {
        when (action) {
            DeleteArticle -> deleteArticle()
            else -> throw IllegalArgumentException("unSupported action : $action")
        }
    }

    init {
        getArticle(savedStateHandle.get<Int>("articleId")!!)
    }

    private fun getArticle(articleId: Int) {
        makeSuspendCall(
            block = {
                remoteRepository.getArticle(articleId)
            },
            onSuccess = { result ->
                updateState { copy(article = result) }
                checkIsOwnArticle(result.authorUsername)
            },
            onError = { error ->
                showSnackbar(error.message.toString())
            },
            onLoading = { isLoading ->
                updateState { copy(isLoadingArticle = isLoading) }
            }
        )
    }

    private fun checkIsOwnArticle(username: String) {
        makeSuspendCall(
            block = {
                dataStoreRepository.getUsername()
            },
            onSuccess = { savedUsername ->
                updateState { copy(isOwnArticle = username == savedUsername) }
            }
        )
    }

    private fun deleteArticle() {
        uiState.value.article?.let { article ->
            makeSuspendCall(
                block = {
                    // todo delete article with articleId
                }
            )
        }
    }
}