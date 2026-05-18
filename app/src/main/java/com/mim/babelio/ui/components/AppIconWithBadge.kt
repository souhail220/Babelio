package com.mim.babelio.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.MenuBook
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mim.babelio.ui.theme.BrownBadge
import com.mim.babelio.ui.theme.BrownPrimary
import com.mim.babelio.ui.theme.StarGold


@Composable
fun AppIconWithBadge(
    icon: ImageVector          = Icons.AutoMirrored.Outlined.MenuBook,
    iconSize: Dp               = 46.dp,
    containerSize: Dp          = 90.dp,
    cornerRadius: Dp           = 22.dp,
    containerColor: Color      = BrownPrimary,
    iconTint: Color            = Color.White,
    badgeIcon: ImageVector     = Icons.Outlined.StarOutline,
    badgeSize: Dp              = 28.dp,
    badgeColor: Color          = BrownBadge,
    badgeTint: Color           = StarGold,
    badgeIconSize: Dp          = 16.dp,
    badgeOffset: Dp            = 6.dp,
) {
    Box(contentAlignment = Alignment.TopEnd) {
        // ── Main icon container ───────────────────────────────────────────
        Box(
            modifier = Modifier
                .size(containerSize)
                .clip(RoundedCornerShape(cornerRadius))
                .background(containerColor),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector     = icon,
                contentDescription = "App icon",
                tint            = iconTint,
                modifier        = Modifier.size(iconSize),
            )
        }

        // ── Badge ─────────────────────────────────────────────────────────
        Box(
            modifier = Modifier
                .offset(x = badgeOffset, y = -badgeOffset)
                .size(badgeSize)
                .clip(CircleShape)
                .background(badgeColor),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector        = badgeIcon,
                contentDescription = null,
                tint               = badgeTint,
                modifier           = Modifier.size(badgeIconSize),
            )
        }
    }
}