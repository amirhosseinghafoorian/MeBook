package com.example.mebook.ui.components

import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
fun rememberAnimationState(initialVisibility: Boolean = false): MutableTransitionState<Boolean> {
    return remember {
        MutableTransitionState(initialVisibility).apply {
            targetState = !initialVisibility
        }
    }
}