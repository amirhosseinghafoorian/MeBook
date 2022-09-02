package com.example.mebook.ui.presentation.write

sealed interface WriteAction {
    data class Publish(val title: String, val body: String) : WriteAction
    object NavigateUp : WriteAction
}