package com.mim.babelio.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mim.babelio.ui.components.AppIconWithBadge
import com.mim.babelio.ui.components.bookComponents.FeatureCard
import com.mim.babelio.ui.components.PrimaryButton
import com.mim.babelio.ui.components.SecondaryButton
import com.mim.babelio.ui.theme.BackgroundCream
import com.mim.babelio.ui.theme.BookNestTheme
import com.mim.babelio.ui.theme.TextPrimary
import com.mim.babelio.ui.theme.TextSecondary

// ── Feature data ──────────────────────────────────────────────────────────────

private data class FeatureItem(
    val icon: ImageVector,
    val title: String,
    val subtitle: String,
)

private val welcomeFeatures = listOf(
    FeatureItem(Icons.Outlined.MenuBook,       "Discover Books",  "Find books tailored to your taste"),
    FeatureItem(Icons.Outlined.FavoriteBorder, "Track Reading",   "Organise your reading list"),
    FeatureItem(Icons.Outlined.StarOutline,    "Share Reviews",   "Connect with book lovers"),
)

// ── Screen ────────────────────────────────────────────────────────────────────

/**
 * Welcome / onboarding screen shown to first-time users.
 *
 * @param onGetStarted Navigate to the sign-up flow.
 * @param onLogin      Navigate to the login flow.
 */
@Composable
fun WelcomeScreen(
    onGetStarted: () -> Unit = {},
    onLogin: () -> Unit      = {},
) {
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { visible = true }

    Box(
        modifier         = Modifier
            .fillMaxSize()
            .background(BackgroundCream),
        contentAlignment = Alignment.Center,
    ) {
        AnimatedVisibility(
            visible = visible,
            enter   = fadeIn(tween(600)) + slideInVertically(tween(600)) { it / 4 },
        ) {
            WelcomeContent(
                visible      = visible,
                onGetStarted = onGetStarted,
                onLogin      = onLogin,
            )
        }
    }
}

// ── Content ───────────────────────────────────────────────────────────────────

@Composable
private fun WelcomeContent(visible: Boolean, onGetStarted: () -> Unit, onLogin: () -> Unit, ) {
    Column(
        modifier              = Modifier
            .fillMaxWidth()
            .padding(horizontal = 28.dp, vertical = 48.dp),
        horizontalAlignment   = Alignment.CenterHorizontally,
        verticalArrangement   = Arrangement.spacedBy(0.dp),
    ) {
        AppIconWithBadge()

        Spacer(Modifier.height(24.dp))

        Text(
            text = "BookNest",
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary,
            letterSpacing = (-0.5).sp,
        )

        Spacer(Modifier.height(12.dp))

        Text(
            text        = "Discover your next favourite book, track\nyour reading journey, and share reviews\nwith a community of readers",
            fontSize    = 15.sp,
            color       = TextSecondary,
            textAlign   = TextAlign.Center,
            lineHeight  = 22.sp,
        )

        Spacer(Modifier.height(40.dp))

        FeatureList(visible = visible)

        Spacer(Modifier.height(48.dp))

        PrimaryButton(text = "Get Started", onClick = onGetStarted)

        Spacer(Modifier.height(12.dp))

        SecondaryButton(text = "I already have an account", onClick = onLogin)
    }
}

// ── Feature list with staggered animation ─────────────────────────────────────

@Composable
private fun FeatureList(visible: Boolean) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        welcomeFeatures.forEachIndexed { index, item ->
            AnimatedVisibility(
                visible = visible,
                enter   = fadeIn(tween(400, delayMillis = 200 + index * 100)) +
                        slideInHorizontally(tween(400, delayMillis = 200 + index * 100)) { -40 },
            ) {
                FeatureCard(
                    icon     = item.icon,
                    title    = item.title,
                    subtitle = item.subtitle,
                )
            }
        }
    }
}

// ── Preview ───────────────────────────────────────────────────────────────────

@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
fun WelcomeScreenPreview() {
    BookNestTheme {
        WelcomeScreen()
    }
}