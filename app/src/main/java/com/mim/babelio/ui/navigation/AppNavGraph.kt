package com.mim.babelio.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mim.babelio.ui.screens.DiscoverScreen
import com.mim.babelio.ui.screens.HomeScreen
import com.mim.babelio.ui.screens.authentication.LoginScreen
import com.mim.babelio.ui.screens.MyBooksScreen
import com.mim.babelio.ui.screens.ProfileScreen
import com.mim.babelio.ui.screens.SearchScreen
import com.mim.babelio.ui.screens.authentication.SignUpScreen
import com.mim.babelio.ui.screens.WelcomeScreen

@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String         = Screen.Welcome.route,
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(Screen.Welcome.route) {
            WelcomeScreen(
                onGetStarted = { navController.navigate(Screen.SignUp.route) },
                onLogin = { navController.navigate(Screen.Login.route) },
            )
        }

        composable(Screen.SignUp.route) {
            SignUpScreen(
                onCreateAccount = { _, _, _ ->
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Welcome.route) { inclusive = true }
                    }
                },
                onSignIn = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.SignUp.route) { inclusive = true }
                    }
                },
            )
        }

        composable(Screen.Login.route) {
            LoginScreen(
                onSignIn = { _, _ ->
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Welcome.route) { inclusive = true }
                    }
                },
                onSignUp = {
                    navController.navigate(Screen.SignUp.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.Home.route) {
            HomeScreen(
                onTabSelected = { tab -> navigateToTab(navController, tab) }
            )
        }

        composable(Screen.MyBooks.route) {
            MyBooksScreen(
                onBookClick   = { /* TODO: book detail */ },
                onTabSelected = { tab -> navigateToTab(navController, tab) },
            )
        }

        composable(Screen.Discover.route) {
            DiscoverScreen(
                onBookClick   = { /* TODO: book detail */ },
                onTabSelected = { tab -> navigateToTab(navController, tab) },
            )
        }

        composable(Screen.Search.route) {
            SearchScreen(
                onSearch      = { /* TODO: navigate to results */ },
                onTabSelected = { tab -> navigateToTab(navController, tab)},
            )
        }

        composable(Screen.Profile.route) {
            ProfileScreen(
                onMyReviews = { /* TODO */ },
                onFavorites = { /* TODO */ },
                onSettings  = { /* TODO */ },
                onSignOut   = {
                    navController.navigate(Screen.Welcome.route) {
                        popUpTo(0) { inclusive = true }
                    }
                },
                onTabSelected = { tab -> navigateToTab(navController, tab) }
            )
        }
    }
}

/**
 * Shared logic for bottom bar navigation to ensure consistency (state saving, single top).
 */
private fun navigateToTab(navController: NavHostController, tab: HomeTab) {
    val route = when (tab) {
        HomeTab.HOME     -> Screen.Home.route
        HomeTab.MY_BOOKS -> Screen.MyBooks.route
        HomeTab.DISCOVER -> Screen.Discover.route
        HomeTab.SEARCH   -> Screen.Search.route
        HomeTab.PROFILE  -> Screen.Profile.route
    }

    // Only navigate if we're not already there (optional check, but good for performance)
    if (navController.currentDestination?.route != route) {
        navController.navigate(route) {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(Screen.Home.route) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }
    }
}

@Composable
private fun PlaceholderTabScreen(
    label: String,
    selectedTab: HomeTab,
    onTabSelected: (HomeTab) -> Unit
) {
    Scaffold(
        bottomBar = {
            BookNestBottomBar(
                selectedTab = selectedTab,
                onTabSelected = onTabSelected
            )
        }
    ) { innerPadding ->
        Box(
            modifier         = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text  = "$label screen — coming soon",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
    }
}
