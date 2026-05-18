package com.mim.babelio.ui.components.bookComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
 * A single feature row card — icon bubble on the left, title + subtitle on the right.
 *
 * @param icon     Vector icon rendered inside the bubble.
 * @param title    Bold feature name.
 * @param subtitle Muted one-line description.
 */
@Composable
fun FeatureCard(
    icon: ImageVector,
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier  = modifier.fillMaxWidth(),
        shape     = RoundedCornerShape(16.dp),
        colors    = CardDefaults.cardColors(containerColor = CardWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
    ) {
        Row(
            modifier             = Modifier.padding(horizontal = 16.dp, vertical = 18.dp),
            verticalAlignment    = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            // ── Icon bubble ───────────────────────────────────────────────
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(IconBg),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector        = icon,
                    contentDescription = null,
                    tint               = BrownLight,
                    modifier           = Modifier.size(24.dp),
                )
            }

            // ── Labels ────────────────────────────────────────────────────
            Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
                Text(
                    text       = title,
                    fontSize   = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color      = TextPrimary,
                )
                Text(
                    text     = subtitle,
                    fontSize = 13.sp,
                    color    = TextSecondary,
                )
            }
        }
    }
}