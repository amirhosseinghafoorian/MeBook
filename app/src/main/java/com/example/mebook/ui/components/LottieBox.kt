package com.example.mebook.ui.components


import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun LottieBox(
    resourceId: Int,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit
) {
    val lottieComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(resourceId))
    Box(modifier) {
        LottieAnimation(
            composition = lottieComposition,
            iterations = Integer.MAX_VALUE,
            contentScale = contentScale
        )
    }
}
