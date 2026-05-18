package com.mim.babelio.ui.components.myBooks

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mim.babelio.ui.components.bookComponents.BookCoverImage
import com.mim.babelio.ui.components.StarRating
import com.mim.babelio.ui.screens.MyBook
import com.mim.babelio.ui.theme.BackgroundCream
import com.mim.babelio.ui.theme.CardWhite
import com.mim.babelio.ui.theme.TextPrimary
import com.mim.babelio.ui.theme.TextSecondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBookCard(
    book: MyBook,
    onClick: () -> Unit,
) {
    Card(
        onClick   = onClick,
        modifier  = Modifier.fillMaxWidth(),
        shape     = RoundedCornerShape(16.dp),
        colors    = CardDefaults.cardColors(containerColor = CardWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp),
        ) {
            // ── Cover ─────────────────────────────────────────────────────
            BookCoverImage(
                coverUrl = book.coverUrl,
                width    = 80.dp,
                cornerRadius = 10.dp,
            )

            // ── Details ───────────────────────────────────────────────────
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Row(
                    modifier              = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment     = Alignment.Top,
                ) {
                    Text(
                        text       = book.title,
                        fontSize   = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color      = TextPrimary,
                        maxLines   = 2,
                        overflow   = TextOverflow.Ellipsis,
                        modifier   = Modifier.weight(1f).padding(end = 8.dp),
                    )

                    // ── Status badge ──────────────────────────────────────
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = BackgroundCream,
                    ) {
                        Text(
                            text     = book.shelf.label,
                            fontSize = 11.sp,
                            color    = TextSecondary,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        )
                    }
                }

                Text(
                    text     = book.author,
                    fontSize = 13.sp,
                    color    = TextSecondary,
                )

                StarRating(rating = book.rating)

                Text(
                    text     = book.synopsis,
                    fontSize = 12.sp,
                    color    = TextSecondary,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    lineHeight = 18.sp,
                )
            }
        }
    }
}