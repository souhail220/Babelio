package com.mim.babelio.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mim.babelio.ui.theme.StarGold
import com.mim.babelio.ui.theme.TextSecondary

/**
 * Inline star icon + numeric rating label.
 *
 * @param rating    Numeric rating to display (e.g. 4.5).
 * @param starSize  Size of the star icon.
 * @param fontSize  Font size of the rating text.
 */
@Composable
fun StarRating(
    rating: Float,
    modifier: Modifier = Modifier,
    starSize: Dp       = 14.dp,
    fontSize: TextUnit = 13.sp,
) {
    Row(
        modifier             = modifier,
        verticalAlignment    = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Icon(
            imageVector        = Icons.Filled.Star,
            contentDescription = "Rating",
            tint               = StarGold,
            modifier           = Modifier.size(starSize),
        )
        Text(
            text     = rating.toString(),
            fontSize = fontSize,
            color    = TextSecondary,
        )
    }
}

