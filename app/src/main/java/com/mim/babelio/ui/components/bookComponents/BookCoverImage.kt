package com.mim.babelio.ui.components.bookComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mim.babelio.ui.theme.BrownLight
import com.mim.babelio.ui.theme.IconBg

/**
 * Book cover image with a fallback placeholder icon.
 * Uses Coil's [AsyncImage] to load remote or local URLs.
 *
 * @param coverUrl     Remote or local image URL. Null shows the placeholder.
 * @param contentDescription Accessibility label.
 * @param width        Fixed width of the cover. Height is derived from a 2:3 ratio.
 * @param cornerRadius Corner radius applied to the clip shape.
 */
@Composable
fun BookCoverImage(
    coverUrl: String?,
    modifier: Modifier      = Modifier,
    contentDescription: String = "Book cover",
    width: Dp               = 72.dp,
    cornerRadius: Dp        = 8.dp,
) {
    Box(
        modifier = modifier
            .size(width = width, height = width * 1.4f)
            .clip(RoundedCornerShape(cornerRadius))
            .background(IconBg),
        contentAlignment = Alignment.Center,
    ) {
        if (!coverUrl.isNullOrBlank()) {
            AsyncImage(
                model              = coverUrl,
                contentDescription = contentDescription,
                contentScale       = ContentScale.Crop,
                modifier           = Modifier.fillMaxSize(),
            )
        } else {
            Icon(
                imageVector        = Icons.Outlined.MenuBook,
                contentDescription = null,
                tint               = BrownLight,
                modifier           = Modifier.size(width * 0.45f),
            )
        }
    }
}