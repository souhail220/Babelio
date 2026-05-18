package com.mim.babelio.ui.components.homeScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mim.babelio.ui.components.bookComponents.BookCoverImage
import com.mim.babelio.ui.components.StarRating
import com.mim.babelio.ui.theme.CardWhite
import com.mim.babelio.ui.theme.TextPrimary
import com.mim.babelio.ui.theme.TextSecondary

/**
 * Horizontal card for the "Continue Reading" section.
 * Shows a cover thumbnail, title, author, star rating, and a reading progress bar.
 *
 * @param coverUrl   Remote URL for the book cover image.
 * @param title      Book title.
 * @param author     Author name.
 * @param rating     Numeric rating (e.g. 4.5).
 * @param progress   Reading progress, 0f–1f.
 * @param onClick    Called when the card is tapped.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContinueReadingCard(
    coverUrl: String?,
    title: String,
    author: String,
    rating: Float,
    progress: Float,
    onClick: () -> Unit    = {},
    modifier: Modifier     = Modifier,
) {
    Card(
        onClick   = onClick,
        modifier  = modifier.fillMaxWidth(),
        shape     = RoundedCornerShape(16.dp),
        colors    = CardDefaults.cardColors(containerColor = CardWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
    ) {
        Row(
            modifier              = Modifier.padding(12.dp),
            verticalAlignment     = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(14.dp),
        ) {
            BookCoverImage(
                coverUrl = coverUrl,
                width = 72.dp,
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Text(
                    text       = title,
                    fontSize   = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color      = TextPrimary,
                    maxLines   = 1,
                )
                Text(
                    text     = author,
                    fontSize = 13.sp,
                    color    = TextSecondary,
                    maxLines = 1,
                )
                StarRating(rating = rating)

                Spacer(Modifier.height(4.dp))

                ReadingProgressBar(progress = progress)
            }
        }
    }
}