package com.mim.babelio.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val BookNestColorScheme = lightColorScheme(
    primary          = BrownPrimary,
    onPrimary        = CardWhite,
    primaryContainer = BrownDark,
    secondary        = BrownLight,
    onSecondary      = CardWhite,
    background       = BackgroundCream,
    onBackground     = TextPrimary,
    surface          = CardWhite,
    onSurface        = TextPrimary,
    surfaceVariant   = IconBg,
    onSurfaceVariant = TextSecondary,
    outline          = ButtonBorder,
)

@Composable
fun BookNestTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = BookNestColorScheme,
        content     = content,
    )
}