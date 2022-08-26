package com.example.mebook.ui.presentation.search

import com.example.mebook.ui.components.text_field_util.BasicTextFieldState
import com.example.mebook.ui.components.text_field_util.TextFieldState

data class SearchUiState(
    val searchState: TextFieldState = BasicTextFieldState()
)