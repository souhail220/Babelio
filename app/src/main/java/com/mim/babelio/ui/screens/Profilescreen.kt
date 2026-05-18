package com.mim.babelio.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mim.babelio.ui.components.profileScreen.ProfileAvatar
import com.mim.babelio.ui.components.profileScreen.ProfileMenuRow
import com.mim.babelio.ui.components.profileScreen.ProfileStatCard
import com.mim.babelio.ui.navigation.BookNestBottomBar
import com.mim.babelio.ui.navigation.HomeTab
import com.mim.babelio.ui.theme.*

// ── Data model ────────────────────────────────────────────────────────────────

data class UserProfile(
    val name: String,
    val bio: String,
    val avatarUrl: String?,
    val booksRead: Int,
    val reviews: Int,
    val favorites: Int,
)

// ── Preview data ──────────────────────────────────────────────────────────────

private val previewProfile = UserProfile(
    name      = "Sirine",
    bio       = "Book enthusiast",
    avatarUrl = null,
    booksRead = 12,
    reviews   = 8,
    favorites = 24,
)

// ── Screen ────────────────────────────────────────────────────────────────────

/**
 * Profile / More screen.
 * Composed entirely from [ProfileAvatar], [ProfileStatCard], [ProfileMenuRow],
 * and [BookNestBottomBar].
 *
 * @param profile        User data to display.
 * @param onMyReviews    Navigate to the reviews list.
 * @param onFavorites    Navigate to the favorites list.
 * @param onSettings     Navigate to settings.
 * @param onSignOut      Sign the user out.
 * @param onTabSelected  Bottom bar tab switching.
 */
@Composable
fun ProfileScreen(
    profile: UserProfile             = previewProfile,
    onMyReviews: () -> Unit          = {},
    onFavorites: () -> Unit          = {},
    onSettings: () -> Unit           = {},
    onSignOut: () -> Unit            = {},
    onTabSelected: (HomeTab) -> Unit = {},
) {
    var showSignOutDialog by remember { mutableStateOf(false) }

    Scaffold(
        containerColor = BackgroundCream,
        bottomBar = {
            BookNestBottomBar(
                selectedTab   = HomeTab.PROFILE,
                onTabSelected = onTabSelected,
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(Modifier.height(32.dp))

            // ── Avatar ────────────────────────────────────────────────────
            ProfileAvatar(
                imageUrl = profile.avatarUrl,
                size     = 96.dp,
            )

            Spacer(Modifier.height(14.dp))

            // ── Name + bio ────────────────────────────────────────────────
            Text(
                text       = profile.name,
                fontSize   = 22.sp,
                fontWeight = FontWeight.Bold,
                color      = TextPrimary,
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text     = profile.bio,
                fontSize = 14.sp,
                color    = TextSecondary,
            )

            Spacer(Modifier.height(28.dp))

            // ── Stat cards ────────────────────────────────────────────────
            ProfileStatsRow(profile = profile)

            Spacer(Modifier.height(28.dp))

            // ── Menu rows ─────────────────────────────────────────────────
            ProfileMenuRow(
                icon     = Icons.Outlined.ChatBubbleOutline,
                title    = "My Reviews",
                subtitle = "View all your reviews",
                onClick  = onMyReviews,
            )

            Spacer(Modifier.height(10.dp))

            ProfileMenuRow(
                icon     = Icons.Outlined.FavoriteBorder,
                title    = "Favorites",
                subtitle = "Your favorite books",
                onClick  = onFavorites,
            )

            Spacer(Modifier.height(10.dp))

            ProfileMenuRow(
                icon     = Icons.Outlined.Settings,
                title    = "Settings",
                subtitle = "App preferences",
                onClick  = onSettings,
            )

            Spacer(Modifier.height(10.dp))

            // Destructive row — red title, pink bubble, no chevron
            ProfileMenuRow(
                icon            = Icons.Outlined.Logout,
                title           = "Sign Out",
                subtitle        = "Sign out of your account",
                iconTint        = Color(0xFFD94F4F),
                iconBubbleColor = Color(0xFFFCECEC),
                titleColor      = Color(0xFFD94F4F),
                showChevron     = false,
                onClick         = { showSignOutDialog = true },
            )

            Spacer(Modifier.height(24.dp))
        }
    }

    // ── Sign-out confirmation dialog ──────────────────────────────────────────
    if (showSignOutDialog) {
        SignOutDialog(
            onConfirm  = { showSignOutDialog = false; onSignOut() },
            onDismiss  = { showSignOutDialog = false },
        )
    }
}

// ── Stat row ──────────────────────────────────────────────────────────────────

@Composable
private fun ProfileStatsRow(profile: UserProfile) {
    Row(
        modifier              = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        ProfileStatCard(
            icon     = Icons.Outlined.MenuBook,
            count    = profile.booksRead,
            label    = "Books Read",
            modifier = Modifier.weight(1f),
        )
        ProfileStatCard(
            icon     = Icons.Outlined.ChatBubbleOutline,
            count    = profile.reviews,
            label    = "Reviews",
            modifier = Modifier.weight(1f),
        )
        ProfileStatCard(
            icon     = Icons.Outlined.FavoriteBorder,
            count    = profile.favorites,
            label    = "Favorites",
            modifier = Modifier.weight(1f),
        )
    }
}

// ── Sign-out dialog ───────────────────────────────────────────────────────────

@Composable
private fun SignOutDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title            = {
            Text(
                text       = "Sign Out",
                fontWeight = FontWeight.Bold,
                color      = TextPrimary,
            )
        },
        text             = {
            Text(
                text  = "Are you sure you want to sign out?",
                color = TextSecondary,
            )
        },
        confirmButton    = {
            TextButton(onClick = onConfirm) {
                Text(text = "Sign Out", color = Color(0xFFD94F4F), fontWeight = FontWeight.SemiBold)
            }
        },
        dismissButton    = {
            TextButton(onClick = onDismiss) {
                Text(text = "Cancel", color = BrownPrimary)
            }
        },
        containerColor   = CardWhite,
    )
}

// ── Preview ───────────────────────────────────────────────────────────────────

@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
fun ProfileScreenPreview() {
    BookNestTheme {
        ProfileScreen()
    }
}