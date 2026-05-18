package com.mim.babelio.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mim.babelio.ui.navigation.BookNestBottomBar
import com.mim.babelio.ui.components.homeScreen.ContinueReadingCard
import com.mim.babelio.ui.navigation.HomeTab
import com.mim.babelio.ui.components.homeScreen.RecommendedBookCard
import com.mim.babelio.ui.components.homeScreen.SectionHeader
import com.mim.babelio.ui.theme.BackgroundCream
import com.mim.babelio.ui.theme.BookNestTheme
import com.mim.babelio.ui.theme.TextPrimary
import com.mim.babelio.ui.theme.TextSecondary

// ── Placeholder data models ───────────────────────────────────────────────────
// Replace these with your real domain models / ViewModel state.

data class BookInProgress(
    val id: String,
    val coverUrl: String?,
    val title: String,
    val author: String,
    val rating: Float,
    val progress: Float,        // 0f – 1f
)

data class BookRecommendation(
    val id: String,
    val coverUrl: String?,
    val title: String,
    val author: String,
    val rating: Float,
)

// ── Static preview data ───────────────────────────────────────────────────────

private val previewInProgress = listOf(
    BookInProgress("1", null, "The Midnight Library", "Matt Haig",     4.5f, 0.6f),
    BookInProgress("2", null, "Where the Crawdads Sing", "Delia Owens", 4.5f, 0.35f),
)

private val previewRecommended = listOf(
    BookRecommendation("3", null, "The Midnight Library", "Matt Haig",   4.5f),
    BookRecommendation("4", null, "Atomic Habits",        "James Clear", 4.8f),
    BookRecommendation("5", null, "Project Hail Mary",    "Andy Weir",   4.7f),
)

// ── Screen ────────────────────────────────────────────────────────────────────

/**
 * Home screen layout scaffold.
 *
 * Wires together all home-specific components inside a [Scaffold] with
 * [BookNestBottomBar]. Feed real data by replacing the preview lists with
 * ViewModel-backed state.
 *
 * @param userName       Display name shown in the greeting.
 * @param booksInProgress List of currently-reading books.
 * @param recommended    List of recommended books.
 * @param onBookClick    Called with the book id when any book is tapped.
 * @param onSeeAllReading  Called when "See all" in Continue Reading is tapped.
 * @param onSeeAllRecommended Called when "See all" in Recommended is tapped.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    userName: String                      = "Sirine",
    booksInProgress: List<BookInProgress> = previewInProgress,
    recommended: List<BookRecommendation> = previewRecommended,
    onBookClick: (String) -> Unit         = {},
    onSeeAllReading: () -> Unit           = {},
    onSeeAllRecommended: () -> Unit       = {},
    onTabSelected: (HomeTab) -> Unit      = {},
) {
    Scaffold(
        containerColor = BackgroundCream,
        bottomBar      = {
            BookNestBottomBar(
                selectedTab   = HomeTab.HOME,
                onTabSelected = onTabSelected,
            )
        },
    ) { innerPadding ->
        LazyColumn(
            modifier            = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding      = PaddingValues(bottom = 24.dp),
        ) {
            // ── Greeting ──────────────────────────────────────────────────
            item {
                GreetingHeader(
                    userName = userName,
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 24.dp),
                )
            }

            // ── Continue Reading ──────────────────────────────────────────
            item {
                SectionHeader(
                    title    = "Continue Reading",
                    onSeeAll = onSeeAllReading,
                    modifier = Modifier.padding(horizontal = 20.dp),
                )
                Spacer(Modifier.height(12.dp))
            }

            items(booksInProgress, key = { it.id }) { book ->
                ContinueReadingCard(
                    coverUrl = book.coverUrl,
                    title    = book.title,
                    author   = book.author,
                    rating   = book.rating,
                    progress = book.progress,
                    onClick  = { onBookClick(book.id) },
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .padding(bottom = 12.dp),
                )
            }

            // ── Recommended for You ───────────────────────────────────────
            item {
                Spacer(Modifier.height(8.dp))
                SectionHeader(
                    title    = "Recommended for You",
                    onSeeAll = onSeeAllRecommended,
                    modifier = Modifier.padding(horizontal = 20.dp),
                )
                Spacer(Modifier.height(12.dp))

                LazyRow(
                    contentPadding      = PaddingValues(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(14.dp),
                ) {
                    items(recommended, key = { it.id }) { book ->
                        RecommendedBookCard(
                            coverUrl  = book.coverUrl,
                            title     = book.title,
                            author    = book.author,
                            rating    = book.rating,
                            cardWidth = 150.dp,
                            onClick   = { onBookClick(book.id) },
                        )
                    }
                }
            }
        }
    }
}

// ── Greeting header ───────────────────────────────────────────────────────────

@Composable
private fun GreetingHeader(
    userName: String,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text       = "Hello, $userName",
            fontSize   = 26.sp,
            fontWeight = FontWeight.Bold,
            color      = TextPrimary,
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text     = "What will you read today?",
            fontSize = 14.sp,
            color    = TextSecondary,
        )
    }
}

// ── Preview ───────────────────────────────────────────────────────────────────

@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
fun HomeScreenPreview() {
    BookNestTheme {
        HomeScreen()
    }
}