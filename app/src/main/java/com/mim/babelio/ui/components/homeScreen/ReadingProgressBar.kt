package com.mim.babelio.ui.components.homeScreen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mim.babelio.ui.theme.BrownPrimary
import com.mim.babelio.ui.theme.ButtonBorder

/**
 * Thin rounded progress bar showing reading completion.
 *
 * @param progress Value between 0f and 1f representing reading completion.
 * @param height   Height of the bar track.
 */
@Composable
fun ReadingProgressBar(
    progress: Float,
    height: Dp         = 6.dp,
    modifier: Modifier = Modifier,
) {
    LinearProgressIndicator(
        progress           = { progress.coerceIn(0f, 1f) },
        modifier           = modifier
            .fillMaxWidth()
            .height(height)
            .clip(CircleShape),
        color              = BrownPrimary,
        trackColor         = ButtonBorder,
    )
}