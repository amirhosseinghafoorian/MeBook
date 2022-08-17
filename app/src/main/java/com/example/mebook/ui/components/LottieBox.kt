package com.example.mebook.ui.components


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun LottieBox(
    resourceId: Int,
    modifier: Modifier = Modifier
) {
    val lottieComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(resourceId))
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
    ) {
        LottieAnimation(lottieComposition, iterations = Integer.MAX_VALUE)
    }
}
