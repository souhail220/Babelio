package com.mim.babelio.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mim.babelio.ui.components.DiscoverSearchBar
import com.mim.babelio.ui.components.homeScreen.SectionHeader
import com.mim.babelio.ui.navigation.BookNestBottomBar
import com.mim.babelio.ui.navigation.HomeTab
import com.mim.babelio.ui.theme.*

// ── Preview data ──────────────────────────────────────────────────────────────

private val recentSearches = listOf(
    "Atomic Habits",
    "The Midnight Library",
    "Science Fiction",
)

// ── Screen ────────────────────────────────────────────────────────────────────

/**
 * Search screen — search bar + recent searches list.
 * Reuses [BookNestSearchBar], [SectionHeader], and [BookNestBottomBar].
 *
 * @param initialRecents  List of recent search strings shown by default.
 * @param onSearch        Called with the query string when the user submits.
 * @param onRecentClick   Called when a recent search row is tapped.
 * @param onTabSelected   Bottom bar tab switching.
 */
@Composable
fun SearchScreen(
    initialRecents: List<String>     = recentSearches,
    onSearch: (String) -> Unit       = {},
    onRecentClick: (String) -> Unit  = {},
    onTabSelected: (HomeTab) -> Unit = {},
) {
    var query   by remember { mutableStateOf("") }
    val recents by remember { mutableStateOf(initialRecents.toMutableStateList()) }
    val focusManager = LocalFocusManager.current

    fun submitSearch(q: String) {
        val trimmed = q.trim()
        if (trimmed.isNotBlank()) {
            // Prepend to recents, deduplicate, cap at 10
            recents.remove(trimmed)
            recents.add(0, trimmed)
            if (recents.size > 10) recents.removeLastOrNull()
            onSearch(trimmed)
            focusManager.clearFocus()
        }
    }

    Scaffold(
        containerColor = BackgroundCream,
        bottomBar = {
            BookNestBottomBar(
                selectedTab   = HomeTab.SEARCH,
                onTabSelected = onTabSelected,
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp),
        ) {
            Spacer(Modifier.height(24.dp))

            // ── Heading ───────────────────────────────────────────────────
            Text(
                text       = "Search",
                fontSize   = 26.sp,
                fontWeight = FontWeight.Bold,
                color      = TextPrimary,
            )

            Spacer(Modifier.height(16.dp))

            // ── Search bar ────────────────────────────────────────────────
            DiscoverSearchBar(
                query         = query,
                onQueryChange = { query = it },
                onSearch      = { submitSearch(query) }
            )

            Spacer(Modifier.height(28.dp))

            // ── Recent searches ───────────────────────────────────────────
            if (recents.isNotEmpty()) {
                SectionHeader(
                    title    = "Recent Searches",
                    onSeeAll = if (recents.size > 3) ({ recents.clear() }) else null,
                )

                Spacer(Modifier.height(12.dp))

                LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(recents, key = { it }) { term ->
                        RecentSearchRow(
                            term    = term,
                            onClick = {
                                query = term
                                onRecentClick(term)
                            },
                        )
                    }
                }
            }
        }
    }
}

// ── Recent search row ─────────────────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RecentSearchRow(
    term: String,
    onClick: () -> Unit,
) {
    Card(
        onClick   = onClick,
        modifier  = Modifier.fillMaxWidth(),
        shape     = RoundedCornerShape(12.dp),
        colors    = CardDefaults.cardColors(containerColor = CardWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
    ) {
        Row(
            modifier              = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            verticalAlignment     = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text       = term,
                fontSize   = 15.sp,
                fontWeight = FontWeight.Medium,
                color      = TextPrimary,
            )
            Icon(
                imageVector        = Icons.Outlined.Search,
                contentDescription = null,
                tint               = TextSecondary,
                modifier           = Modifier.size(18.dp),
            )
        }
    }
}

// ── Preview ───────────────────────────────────────────────────────────────────

@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
fun SearchScreenPreview() {
    BookNestTheme {
        SearchScreen()
    }
}