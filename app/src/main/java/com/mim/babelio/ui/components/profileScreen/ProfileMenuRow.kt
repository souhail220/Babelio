package com.mim.babelio.ui.components.profileScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mim.babelio.ui.theme.BrownLight
import com.mim.babelio.ui.theme.CardWhite
import com.mim.babelio.ui.theme.IconBg
import com.mim.babelio.ui.theme.TextPrimary
import com.mim.babelio.ui.theme.TextSecondary

/**
 * Tappable menu row used in the profile screen action list.
 * Shows an icon bubble, title, subtitle, and a chevron on the right.
 *
 * @param icon          Icon rendered inside the bubble.
 * @param title         Primary label (e.g. "My Reviews").
 * @param subtitle      Secondary description (e.g. "View all your reviews").
 * @param iconTint      Tint color for the icon.
 * @param iconBubbleColor Background color of the icon bubble.
 * @param titleColor    Color of the title text — override for destructive actions (red).
 * @param showChevron   Whether to show the right-arrow. False for destructive rows.
 * @param onClick       Called when the row is tapped.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileMenuRow(
    icon: ImageVector,
    title: String,
    subtitle: String,
    iconTint: Color        = BrownLight,
    iconBubbleColor: Color = IconBg,
    titleColor: Color      = TextPrimary,
    showChevron: Boolean   = true,
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
            modifier              = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            verticalAlignment     = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(14.dp),
        ) {
            // Icon bubble — reuses same style as FeatureCard
            Card(
                shape  = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = iconBubbleColor),
            ) {
                Icon(
                    imageVector        = icon,
                    contentDescription = null,
                    tint               = iconTint,
                    modifier           = Modifier
                        .size(44.dp)
                        .padding(10.dp),
                )
            }

            // Labels
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(2.dp),
            ) {
                Text(
                    text       = title,
                    fontSize   = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color      = titleColor,
                )
                Text(
                    text     = subtitle,
                    fontSize = 13.sp,
                    color    = TextSecondary,
                )
            }

            if (showChevron) {
                Icon(
                    imageVector        = Icons.Outlined.ChevronRight,
                    contentDescription = null,
                    tint               = TextSecondary,
                    modifier           = Modifier.size(20.dp),
                )
            }
        }
    }
}