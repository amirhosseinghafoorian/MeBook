package com.example.mebook.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = GREEN,
    primaryVariant = Purple700,
    secondary = WHITE,
    background = DARK_BLUE,
    onBackground = WHITE
)

private val LightColorPalette = lightColors(
    primary = GREEN,
    primaryVariant = Purple700,
    secondary = DARK_BLUE,
    background = GRAY.copy(alpha = 0.2f),
    onBackground = BLACK,
    surface = WHITE,
    onSurface = BLACK,
    onError = Color.Red
)

@Composable
fun MeBookTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}