package com.example.mebook.ui.components

import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

@Composable
fun AnimationStateReactor(
    animationState: MutableTransitionState<Boolean>,
    key : Boolean
) {
    LaunchedEffect(key) {
        animationState.targetState = key
    }
}