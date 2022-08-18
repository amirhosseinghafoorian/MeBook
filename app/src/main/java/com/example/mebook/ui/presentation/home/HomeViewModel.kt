package com.example.mebook.ui.presentation.home

import com.example.mebook.ui.util.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : BaseViewModel<HomeAction, HomeUiState>(HomeUiState()) {
    override fun onAction(action: HomeAction) {}
}