package com.example.mebook.ui.util

import androidx.compose.runtime.Composable


@Composable
fun Boolean.doOnTrue(block: @Composable () -> Unit): Boolean {
    if (this) block()
    return this
}

@Composable
fun Boolean.doOnFalse(block: @Composable () -> Unit): Boolean {
    if (!this) block()
    return this
}