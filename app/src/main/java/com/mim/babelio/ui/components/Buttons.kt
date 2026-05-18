package com.mim.babelio.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mim.babelio.ui.theme.BrownPrimary
import com.mim.babelio.ui.theme.ButtonBorder
import com.mim.babelio.ui.theme.TextPrimary

private val ButtonShape  = RoundedCornerShape(16.dp)
private val ButtonHeight = 56.dp

/**
 * Filled primary action button — uses [BrownPrimary] as container colour.
 *
 * @param text    Label shown on the button.
 * @param onClick Called when the button is tapped.
 */
@Composable
fun PrimaryButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier, ) {
    Button(
        onClick  = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(ButtonHeight)
            .shadow(elevation = 8.dp, shape = ButtonShape),
        shape  = ButtonShape,
        colors = ButtonDefaults.buttonColors(containerColor = BrownPrimary),
    ) {
        Text(
            text          = text,
            fontSize      = 16.sp,
            fontWeight    = FontWeight.SemiBold,
            color         = Color.White,
            letterSpacing = 0.3.sp,
        )
    }
}

/**
 * Outlined secondary action button — transparent background with a subtle border.
 *
 * @param text    Label shown on the button.
 * @param onClick Called when the button is tapped.
 */
@Composable
fun SecondaryButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    OutlinedButton(
        onClick  = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(ButtonHeight),
        shape  = ButtonShape,
        colors = ButtonDefaults.outlinedButtonColors(contentColor = TextPrimary),
        border = BorderStroke(width = 1.5.dp, color = ButtonBorder),
    ) {
        Text(
            text          = text,
            fontSize      = 16.sp,
            fontWeight    = FontWeight.Medium,
            letterSpacing = 0.2.sp,
        )
    }
}