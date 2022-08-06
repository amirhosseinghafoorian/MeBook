package com.example.mebook.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.mebook.navigation.MeBookScreens.HOME_ROUTE
import com.example.mebook.navigation.MeBookScreens.SEARCH_ROUTE
import com.example.mebook.navigation.MeBookScreens.WRITE_ROUTE

sealed class BottomNavItem(
    var title: String,
    var icon: ImageVector,
    var screen_route: String
) {
    object Home : BottomNavItem("Home", Icons.Default.Home, HOME_ROUTE)
    object Write : BottomNavItem("Write", Icons.Default.Add, WRITE_ROUTE)
    object Search : BottomNavItem("Search", Icons.Default.Search, SEARCH_ROUTE)
}