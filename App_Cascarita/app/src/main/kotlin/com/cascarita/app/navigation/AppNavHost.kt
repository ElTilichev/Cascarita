package com.cascarita.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cascarita.app.feature.game.presentation.ScoreboardScreen
import com.cascarita.app.feature.team.presentation.TeamsScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Scoreboard.route,
        modifier = modifier
    ) {
        composable(Screen.Scoreboard.route) {
            ScoreboardScreen(
                onNavigateToTeams = {
                    navController.navigate(Screen.Teams.route)
                }
            )
        }
        
        composable(Screen.Teams.route) {
            TeamsScreen()
        }
        
        composable(Screen.History.route) {
            // TODO: Implement History Screen
        }
        
        composable(Screen.Settings.route) {
            // TODO: Implement Settings Screen
        }
    }
}
