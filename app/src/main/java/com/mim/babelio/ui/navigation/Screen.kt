package com.mim.babelio.ui.navigation

/**
 * Single source of truth for every navigation destination in the app.
 * Add new routes here and reference them in [AppNavGraph].
 */
sealed class Screen(val route: String) {
    data object Welcome : Screen("welcome")
    data object SignUp  : Screen("sign_up")
    data object Login   : Screen("login")
    data object Home    : Screen("home")

    data object MyBooks: Screen("my_books")
    data object Discover: Screen("discover")
    data object Search: Screen("search")
    data object Profile: Screen("profile")
}