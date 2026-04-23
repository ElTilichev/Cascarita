package com.cascarita.app.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Scoreboard : Screen("scoreboard")
    object Teams : Screen("teams")
    object Settings : Screen("settings")
}
