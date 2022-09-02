package com.example.mebook.ui.components

import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun rememberAnimationState(
    initialVisibility: Boolean = false,
    blockStartUp: Boolean = false
): MutableTransitionState<Boolean> {

    var isStartUp by remember {
        mutableStateOf(true)
    }

    return remember {
        MutableTransitionState(initialVisibility).apply {
            if (blockStartUp) {
                if (!isStartUp) {
                    targetState = !initialVisibility
                } else {
                    isStartUp = false
                }
            } else {
                targetState = !initialVisibility
            }

        }
    }
}