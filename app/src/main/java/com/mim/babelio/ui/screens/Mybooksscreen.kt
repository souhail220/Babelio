package com.mim.babelio.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mim.babelio.ui.components.bookComponents.BookCoverImage
import com.mim.babelio.ui.navigation.BookNestBottomBar
import com.mim.babelio.ui.navigation.HomeTab
import com.mim.babelio.ui.components.StarRating
import com.mim.babelio.ui.components.myBooks.MyBookCard
import com.mim.babelio.ui.theme.BackgroundCream
import com.mim.babelio.ui.theme.BookNestTheme
import com.mim.babelio.ui.theme.CardWhite
import com.mim.babelio.ui.theme.TextPrimary
import com.mim.babelio.ui.theme.TextSecondary

// ── Tab model ─────────────────────────────────────────────────────────────────

enum class BookShelfTab(val label: String) {
    READING      ("Reading"),
    WANT_TO_READ ("Want to Read"),
    FINISHED     ("Finished"),
}

// ── Data model ────────────────────────────────────────────────────────────────

data class MyBook(
    val id: String,
    val coverUrl: String?,
    val title: String,
    val author: String,
    val rating: Float,
    val synopsis: String,
    val shelf: BookShelfTab,
)

// ── Preview data ──────────────────────────────────────────────────────────────

private val previewBooks = listOf(
    MyBook(
        id       = "1",
        coverUrl = null,
        title    = "The Midnight Library",
        author   = "Matt Haig",
        rating   = 4.5f,
        synopsis = "Between life and death there is a library, and within that library, the shelves go o...",
        shelf    = BookShelfTab.READING,
    ),
    MyBook(
        id       = "2",
        coverUrl = null,
        title    = "Where the Crawdads Sing",
        author   = "Delia Owens",
        rating   = 4.5f,
        synopsis = "A murder mystery set in the marshlands of North Carolina, following a young...",
        shelf    = BookShelfTab.READING,
    ),
    MyBook(
        id       = "3",
        coverUrl = null,
        title    = "Atomic Habits",
        author   = "James Clear",
        rating   = 4.8f,
        synopsis = "An easy and proven way to build good habits and break bad ones...",
        shelf    = BookShelfTab.WANT_TO_READ,
    ),
    MyBook(
        id       = "4",
        coverUrl = null,
        title    = "Project Hail Mary",
        author   = "Andy Weir",
        rating   = 4.7f,
        synopsis = "A lone astronaut must save the earth from disaster in this propulsive science thriller...",
        shelf    = BookShelfTab.FINISHED,
    ),
)

// ── Screen ────────────────────────────────────────────────────────────────────

/**
 * My Books screen — tabbed shelf view (Reading / Want to Read / Finished).
 * Uses [BookCoverImage], [StarRating], and [BookNestBottomBar] from the shared library.
 *
 * @param books       Full book list across all shelves; filtered by selected tab.
 * @param onBookClick Called with the book id when a card is tapped.
 */
@Composable
fun MyBooksScreen(
    books: List<MyBook>           = previewBooks,
    onBookClick: (String) -> Unit = {},
    onTabSelected: (HomeTab) -> Unit = {},
) {
    var selectedShelf by remember { mutableStateOf(BookShelfTab.READING) }

    Scaffold(
        containerColor = BackgroundCream,
        bottomBar = {
            BookNestBottomBar(
                selectedTab   = HomeTab.MY_BOOKS,
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
                text       = "My Books",
                fontSize   = 26.sp,
                fontWeight = FontWeight.Bold,
                color      = TextPrimary,
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text     = "Your reading collection",
                fontSize = 14.sp,
                color    = TextSecondary,
            )

            Spacer(Modifier.height(24.dp))

            // ── Shelf tabs ────────────────────────────────────────────────
            ShelfTabRow(
                selectedTab = selectedShelf,
                onTabSelected = { selectedShelf = it },
            )

            Spacer(Modifier.height(20.dp))

            // ── Book list ─────────────────────────────────────────────────
            val filtered = books.filter { it.shelf == selectedShelf }

            if (filtered.isEmpty()) {
                EmptyShelfMessage(tab = selectedShelf)
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding      = PaddingValues(bottom = 24.dp),
                ) {
                    items(filtered, key = { it.id }) { book ->
                        MyBookCard(
                            book    = book,
                            onClick = { onBookClick(book.id) },
                        )
                    }
                }
            }
        }
    }
}

// ── Shelf tab row ─────────────────────────────────────────────────────────────

@Composable
private fun ShelfTabRow(
    selectedTab: BookShelfTab,
    onTabSelected: (BookShelfTab) -> Unit,
) {
    Surface(
        shape  = RoundedCornerShape(14.dp),
        color  = CardWhite,
        shadowElevation = 1.dp,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            BookShelfTab.entries.forEach { tab ->
                val selected = tab == selectedTab
                Surface(
                    onClick      = { onTabSelected(tab) },
                    modifier     = Modifier.weight(1f),
                    shape        = RoundedCornerShape(10.dp),
                    color        = if (selected) CardWhite else Color.Transparent,
                    shadowElevation = if (selected) 2.dp else 0.dp,
                ) {
                    Text(
                        text       = tab.label,
                        fontSize   = 13.sp,
                        fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal,
                        color      = if (selected) TextPrimary else TextSecondary,
                        modifier   = Modifier
                            .padding(vertical = 10.dp)
                            .wrapContentWidth(Alignment.CenterHorizontally),
                    )
                }
            }
        }
    }
}

// ── Empty state ───────────────────────────────────────────────────────────────

@Composable
private fun EmptyShelfMessage(tab: BookShelfTab) {
    Box(
        modifier         = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text      = "No books in \"${tab.label}\" yet.",
            fontSize  = 14.sp,
            color     = TextSecondary,
        )
    }
}

// ── Preview ───────────────────────────────────────────────────────────────────

@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
fun MyBooksScreenPreview() {
    BookNestTheme {
        MyBooksScreen()
    }
}