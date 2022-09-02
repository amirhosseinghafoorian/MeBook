package com.example.mebook.ui.presentation.write

sealed interface WriteAction {
    object Publish : WriteAction
    object NavigateUp : WriteAction
}