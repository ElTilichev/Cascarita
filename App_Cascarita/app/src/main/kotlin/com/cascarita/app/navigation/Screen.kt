package com.cascarita.app.navigation

sealed class Screen(val route: String) {
    object Scoreboard : Screen("scoreboard")
    object Teams : Screen("teams")
    object History : Screen("history")
    object Settings : Screen("settings")
}
