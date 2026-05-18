package com.mim.babelio.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.LibraryBooks
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mim.babelio.ui.theme.BrownPrimary
import com.mim.babelio.ui.theme.CardWhite
import com.mim.babelio.ui.theme.TextSecondary

// ── Tab model ─────────────────────────────────────────────────────────────────

enum class HomeTab(val label: String, val icon: ImageVector) {
    HOME      ("Home",      Icons.Outlined.Home),
    MY_BOOKS  ("My Books", Icons.AutoMirrored.Outlined.LibraryBooks),
    DISCOVER  ("Discover",  Icons.Outlined.Explore),
    SEARCH    ("Search",    Icons.Outlined.Search),
    PROFILE   ("Profile",   Icons.Outlined.Person),
}

/**
 * App-wide bottom navigation bar.
 *
 * @param selectedTab   Currently active [HomeTab].
 * @param onTabSelected Called with the newly tapped [HomeTab].
 */
@Composable
fun BookNestBottomBar(
    selectedTab: HomeTab,
    onTabSelected: (HomeTab) -> Unit,
) {
    NavigationBar(
        containerColor = CardWhite,
        tonalElevation = 0.dp,
    ) {
        HomeTab.entries.forEach { tab ->
            val selected = tab == selectedTab
            NavigationBarItem(
                selected  = selected,
                onClick   = { onTabSelected(tab) },
                icon      = {
                    Icon(
                        imageVector        = tab.icon,
                        contentDescription = tab.label,
                    )
                },
                label     = {
                    Text(
                        text     = tab.label,
                        fontSize = 10.sp,
                    )
                },
                colors    = NavigationBarItemDefaults.colors(
                    selectedIconColor   = BrownPrimary,
                    selectedTextColor   = BrownPrimary,
                    unselectedIconColor = TextSecondary,
                    unselectedTextColor = TextSecondary,
                    indicatorColor      = Color.Transparent,
                ),
            )
        }
    }
}