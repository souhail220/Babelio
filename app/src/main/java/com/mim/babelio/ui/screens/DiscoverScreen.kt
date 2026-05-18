package com.mim.babelio.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mim.babelio.ui.components.DiscoverSearchBar
import com.mim.babelio.ui.components.StarRating
import com.mim.babelio.ui.components.bookComponents.BookCoverImage
import com.mim.babelio.ui.components.homeScreen.SectionHeader
import com.mim.babelio.ui.navigation.BookNestBottomBar
import com.mim.babelio.ui.navigation.HomeTab
import com.mim.babelio.ui.theme.*

// ── Data models ───────────────────────────────────────────────────────────────

data class DiscoverBook(
    val id: String,
    val coverUrl: String?,
    val title: String,
    val author: String,
    val rating: Float,
    val genre: String,
)

// ── Preview data ──────────────────────────────────────────────────────────────

private val genres = listOf(
    "Fiction", "Science Fiction", "Mystery", "Thriller",
    "Romance", "Fantasy", "Self-Help", "Biography",
    "History", "Psychology",
)

private val popularBooks = listOf(
    DiscoverBook("1", null, "The Midnight Library", "Matt Haig",   4.5f, "Fiction"),
    DiscoverBook("2", null, "Atomic Habits",        "James Clear", 4.8f, "Self-Help"),
    DiscoverBook("3", null, "Project Hail Mary",    "Andy Weir",   4.7f, "Science Fiction"),
    DiscoverBook("4", null, "Zero to One",          "Peter Thiel", 4.6f, "Business"),
)

// ── Screen ────────────────────────────────────────────────────────────────────

/**
 * Discover screen — search bar, genre chips, and a 2-column popular grid.
 * Reuses [BookCoverImage], [StarRating], [SectionHeader], and [BookNestBottomBar].
 *
 * @param books           Full book pool; filtered live as the search query changes.
 * @param onBookClick     Called with the book id when a card is tapped.
 * @param onGenreClick    Called with the genre label when a chip is tapped.
 * @param onTabSelected   Bottom bar tab selection.
 */
@Composable
fun DiscoverScreen(
    books: List<DiscoverBook>     = popularBooks,
    onBookClick: (String) -> Unit = {},
    onGenreClick: (String) -> Unit = {},
    onTabSelected: (HomeTab) -> Unit = {},
) {
    var query          by remember { mutableStateOf("") }
    var selectedGenre  by remember { mutableStateOf<String?>(null) }
    val focusManager   = LocalFocusManager.current

    val filtered = books.filter { book ->
        val matchesQuery = query.isBlank() ||
                book.title.contains(query, ignoreCase = true) ||
                book.author.contains(query, ignoreCase = true)
        val matchesGenre = selectedGenre == null || book.genre == selectedGenre
        matchesQuery && matchesGenre
    }

    Scaffold(
        containerColor = BackgroundCream,
        bottomBar = {
            BookNestBottomBar(
                selectedTab   = HomeTab.DISCOVER,
                onTabSelected = onTabSelected,
            )
        },
    ) { innerPadding ->
        LazyColumn(
            modifier       = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(bottom = 24.dp),
        ) {
            // ── Heading ───────────────────────────────────────────────────
            item {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .padding(top = 24.dp, bottom = 20.dp),
                ) {
                    Text(
                        text       = "Discover",
                        fontSize   = 26.sp,
                        fontWeight = FontWeight.Bold,
                        color      = TextPrimary,
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text     = "Explore new books",
                        fontSize = 14.sp,
                        color    = TextSecondary,
                    )
                }
            }

            // ── Search bar ────────────────────────────────────────────────
            item {
                DiscoverSearchBar(
                    query         = query,
                    onQueryChange = { query = it },
                    onSearch      = { focusManager.clearFocus() },
                    modifier      = Modifier
                        .padding(horizontal = 20.dp)
                        .padding(bottom = 24.dp),
                )
            }

            // ── Browse by Genre ───────────────────────────────────────────
            item {
                SectionHeader(
                    title    = "Browse by Genre",
                    modifier = Modifier.padding(horizontal = 20.dp),
                )
                Spacer(Modifier.height(14.dp))
                GenreChips(
                    genres         = genres,
                    selectedGenre  = selectedGenre,
                    onGenreClick   = { genre ->
                        selectedGenre = if (selectedGenre == genre) null else genre
                        onGenreClick(genre)
                    },
                )
                Spacer(Modifier.height(28.dp))
            }

            // ── Popular This Week ─────────────────────────────────────────
            item {
                SectionHeader(
                    title    = "Popular This Week",
                    onSeeAll = { /* TODO */ },
                    modifier = Modifier.padding(horizontal = 20.dp),
                )
                Spacer(Modifier.height(14.dp))
            }

            // 2-column grid — emulated with pairs since LazyVerticalGrid
            // can't live inside a LazyColumn. We chunk into rows of 2.
            val rows = filtered.chunked(2)
            items(rows) { row ->
                Row(
                    modifier              = Modifier
                        .padding(horizontal = 20.dp)
                        .padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(14.dp),
                ) {
                    row.forEach { book ->
                        PopularBookCard(
                            book      = book,
                            onClick   = { onBookClick(book.id) },
                            modifier  = Modifier.weight(1f),
                        )
                    }
                    // Fill empty slot if odd number
                    if (row.size == 1) Spacer(Modifier.weight(1f))
                }
            }

            if (filtered.isEmpty()) {
                item {
                    Box(
                        modifier         = Modifier
                            .fillMaxWidth()
                            .padding(top = 40.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text     = "No books found for \"$query\"",
                            fontSize = 14.sp,
                            color    = TextSecondary,
                        )
                    }
                }
            }
        }
    }
}


// ── Genre chips ───────────────────────────────────────────────────────────────

@Composable
private fun GenreChips(genres: List<String>, selectedGenre: String?, onGenreClick: (String) -> Unit) {
    // Wrap chips using a flow-style layout via chunked rows
    val rows = genres.chunked(3)
    Column(
        modifier            = Modifier.padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        rows.forEach { row ->
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                row.forEach { genre ->
                    val selected = genre == selectedGenre
                    Surface(
                        onClick = { onGenreClick(genre) },
                        shape   = CircleShape,
                        color   = if (selected) BrownPrimary else CardWhite,
                        border  = BorderStroke(
                            width = 1.dp,
                            color = if (selected) BrownPrimary else ButtonBorder,
                        ),
                    ) {
                        Text(
                            text     = genre,
                            fontSize = 13.sp,
                            color    = if (selected) Color.White else TextPrimary,
                            modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
                        )
                    }
                }
            }
        }
    }
}

// ── Popular book card (cover-first vertical layout) ───────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PopularBookCard(
    book: DiscoverBook,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        // Full-width cover — aspect ratio 2:3
        BookCoverImage(
            coverUrl     = book.coverUrl,
            width        = 200.dp,   // intrinsic; actual width is driven by weight()
            cornerRadius = 12.dp,
            modifier     = Modifier.fillMaxWidth(),
        )

        Text(
            text       = book.title,
            fontSize   = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color      = TextPrimary,
            maxLines   = 2,
            overflow   = TextOverflow.Ellipsis,
        )

        Text(
            text     = book.author,
            fontSize = 12.sp,
            color    = TextSecondary,
            maxLines = 1,
        )

        Row(
            verticalAlignment    = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            StarRating(rating = book.rating, starSize = 12.dp, fontSize = 12.sp)
            Text(text = "•", fontSize = 12.sp, color = TextSecondary)
            Text(text = book.genre, fontSize = 12.sp, color = TextSecondary)
        }
    }
}

// ── Preview ───────────────────────────────────────────────────────────────────

@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
fun DiscoverScreenPreview() {
    BookNestTheme {
        DiscoverScreen()
    }
}