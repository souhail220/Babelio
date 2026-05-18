package com.mim.babelio.ui.components.homeScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mim.babelio.ui.components.bookComponents.BookCoverImage
import com.mim.babelio.ui.components.StarRating
import com.mim.babelio.ui.theme.TextPrimary
import com.mim.babelio.ui.theme.TextSecondary

/**
 * Vertical book card used in horizontal recommendation rows.
 * No Card wrapper — just cover + text, keeping it lightweight and flexible.
 *
 * @param coverUrl  Remote URL for the book cover.
 * @param title     Book title.
 * @param author    Author name.
 * @param rating    Numeric rating.
 * @param cardWidth Width of the entire card; cover fills this width.
 * @param onClick   Called when the card is tapped.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecommendedBookCard(
    coverUrl: String?,
    title: String,
    author: String,
    rating: Float,
    modifier: Modifier  = Modifier,
    cardWidth: Dp      = 150.dp,
    onClick: () -> Unit = {},
) {
    Column(
        modifier            = modifier.width(cardWidth),
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        BookCoverImage(
            coverUrl = coverUrl,
            width = cardWidth,
            cornerRadius = 12.dp,
        )

        Text(
            text       = title,
            fontSize   = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color      = TextPrimary,
            maxLines   = 2,
            overflow   = TextOverflow.Ellipsis,
        )

        Text(
            text     = author,
            fontSize = 12.sp,
            color    = TextSecondary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

        StarRating(rating = rating, starSize = 12.dp, fontSize = 12.sp)
    }
}