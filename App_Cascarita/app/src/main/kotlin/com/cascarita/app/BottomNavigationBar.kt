package com.cascarita.app

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.cascarita.app.core.theme.*
import com.cascarita.app.navigation.Screen
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Scoreboard
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Settings

data class BottomNavItem(
    val screen: Screen,
    val title: String,
    val icon: ImageVector
)

val bottomNavItems = listOf(
    BottomNavItem(Screen.Scoreboard, "Marcador", Icons.Default.Scoreboard),
    BottomNavItem(Screen.Teams, "Equipos", Icons.Default.Groups),
    BottomNavItem(Screen.Settings, "Ajustes", Icons.Default.Settings)
)

@Composable
fun BottomNavigationBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar(
        containerColor = Surface,
        tonalElevation = 0.dp
    ) {
        bottomNavItems.forEach { item ->
            AddNavItem(
                navController = navController,
                item = item,
                currentDestination = currentDestination
            )
        }
    }
}

@Composable
fun RowScope.AddNavItem(
    navController: NavController,
    item: BottomNavItem,
    currentDestination: androidx.navigation.NavDestination?
) {
    val isSelected = currentDestination?.hierarchy?.any { it.route == item.screen.route } == true

    NavigationBarItem(
        selected = isSelected,
        onClick = {
            navController.navigate(item.screen.route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        },
        icon = {
            ColumnWithIcon(
                icon = item.icon,
                label = item.title,
                isSelected = isSelected
            )
        },
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = Primary,
            selectedTextColor = Primary,
            unselectedIconColor = Secondary.copy(alpha = 0.6f),
            unselectedTextColor = Secondary.copy(alpha = 0.6f),
            indicatorColor = Primary.copy(alpha = 0.1f)
        )
    )
}

@Composable
fun ColumnWithIcon(
    icon: ImageVector,
    label: String,
    isSelected: Boolean
) {
    androidx.compose.foundation.layout.Column(
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = label,
            style = androidx.compose.ui.text.TextStyle(
                fontSize = 10.sp
            )
        )
    }
}
