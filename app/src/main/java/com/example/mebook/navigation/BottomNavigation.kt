package com.example.mebook.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.mebook.navigation.MeBookScreens.PROFILE_ROUTE
import com.example.mebook.navigation.MeBookScreens.WRITE_ROUTE

@Composable
fun BottomNavigation(
    navController: NavController,
    mainNavController: NavController
) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Write,
        BottomNavItem.Search,
        BottomNavItem.Profile,
    )

    BottomNavigation(
        modifier = Modifier
            .padding(16.dp)
            .clip(MaterialTheme.shapes.medium),
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onSurface
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        fontSize = 12.sp,
                    )
                },
                selectedContentColor = MaterialTheme.colors.secondary,
                unselectedContentColor = MaterialTheme.colors.surface,
                alwaysShowLabel = true,
                selected = currentRoute == item.screen_route,
                onClick = {
                    if (
                        item.screen_route == WRITE_ROUTE ||
                        item.screen_route == PROFILE_ROUTE
                    ) {
                        mainNavController.navigate(item.screen_route) {

                            navController.graph.startDestinationRoute?.let { screen_route ->
                                popUpTo(screen_route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    } else {
                        navController.navigate(item.screen_route) {

                            navController.graph.startDestinationRoute?.let { screen_route ->
                                popUpTo(screen_route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}