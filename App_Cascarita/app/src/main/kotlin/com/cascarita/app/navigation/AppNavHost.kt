package com.cascarita.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cascarita.app.feature.game.presentation.ScoreboardScreen
import com.cascarita.app.feature.team.presentation.TeamsScreen
import com.cascarita.app.feature.splash.presentation.SplashScreen
import com.cascarita.app.feature.settings.presentation.SettingsScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route,
        modifier = modifier
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(
                onNavigateNext = {
                    navController.navigate(Screen.Scoreboard.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }

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
        
        composable(Screen.Settings.route) {
            SettingsScreen()
        }
    }
}
