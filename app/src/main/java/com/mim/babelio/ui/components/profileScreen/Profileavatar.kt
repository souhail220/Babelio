package com.mim.babelio.ui.components.profileScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mim.babelio.ui.theme.BrownLight
import com.mim.babelio.ui.theme.IconBg

/**
 * Circular profile avatar with a Coil [AsyncImage] and a fallback person icon.
 *
 * @param imageUrl    Remote or local photo URL. Null shows the placeholder.
 * @param size        Diameter of the circle.
 * @param borderWidth Stroke width of the subtle border ring. Pass 0.dp for none.
 * @param borderColor Color of the border ring.
 */
@Composable
fun ProfileAvatar(
    imageUrl: String?,
    size: Dp           = 96.dp,
    borderWidth: Dp    = 2.dp,
    borderColor: Color = Color(0xFFD8CCBE),
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(IconBg)
            .then(
                if (borderWidth > 0.dp)
                    Modifier.border(borderWidth, borderColor, CircleShape)
                else Modifier
            ),
        contentAlignment = Alignment.Center,
    ) {
        if (!imageUrl.isNullOrBlank()) {
            AsyncImage(
                model              = imageUrl,
                contentDescription = "Profile photo",
                contentScale       = ContentScale.Crop,
                modifier           = Modifier
                    .size(size)
                    .clip(CircleShape),
            )
        } else {
            Icon(
                imageVector        = Icons.Outlined.Person,
                contentDescription = null,
                tint               = BrownLight,
                modifier           = Modifier.size(size * 0.5f),
            )
        }
    }
}