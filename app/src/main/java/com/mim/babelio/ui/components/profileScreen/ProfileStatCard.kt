package com.mim.babelio.ui.components.profileScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
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
 * Compact stat tile — icon, large number, and a label underneath.
 * Used in a row of three on the profile header (Books Read / Reviews / Favorites).
 *
 * @param icon  Vector icon displayed above the count.
 * @param count Numeric stat value.
 * @param label Short description label below the count (e.g. "Books Read").
 */
@Composable
fun ProfileStatCard(
    icon: ImageVector,
    count: Int,
    label: String,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier  = modifier,
        shape     = RoundedCornerShape(16.dp),
        colors    = CardDefaults.cardColors(containerColor = CardWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
    ) {
        Column(
            modifier            = Modifier
                .size(width = 110.dp, height = 110.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            // Icon bubble
            Card(
                shape  = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(containerColor = IconBg),
            ) {
                Icon(
                    imageVector        = icon,
                    contentDescription = null,
                    tint               = BrownLight,
                    modifier           = Modifier
                        .size(40.dp)
                        .padding(8.dp),
                )
            }

            Spacer(Modifier.height(8.dp))

            Text(
                text       = count.toString(),
                fontSize   = 22.sp,
                fontWeight = FontWeight.Bold,
                color      = TextPrimary,
            )

            Text(
                text     = label,
                fontSize = 12.sp,
                color    = TextSecondary,
            )
        }
    }
}