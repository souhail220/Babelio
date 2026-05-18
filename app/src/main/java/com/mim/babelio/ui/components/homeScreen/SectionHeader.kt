package com.mim.babelio.ui.components.homeScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.mim.babelio.ui.theme.BrownPrimary
import com.mim.babelio.ui.theme.TextPrimary

/**
 * Section heading row with an optional "See all" action on the right.
 *
 * @param title     Section label (e.g. "Continue Reading").
 * @param onSeeAll  When non-null, a "See all" button is shown and calls this lambda.
 */
@Composable
fun SectionHeader(
    title: String,
    modifier: Modifier = Modifier,
    onSeeAll: (() -> Unit)? = null,
) {
    Row(
        modifier              = modifier.fillMaxWidth(),
        verticalAlignment     = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text       = title,
            fontSize   = 18.sp,
            fontWeight = FontWeight.Bold,
            color      = TextPrimary,
        )

        if (onSeeAll != null) {
            TextButton(onClick = onSeeAll) {
                Text(
                    text       = "See all",
                    fontSize   = 13.sp,
                    fontWeight = FontWeight.Medium,
                    color      = BrownPrimary,
                )
            }
        }
    }
}