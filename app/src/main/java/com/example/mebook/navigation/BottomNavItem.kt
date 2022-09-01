package com.example.mebook.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.mebook.navigation.MeBookScreens.HomeRoute
import com.example.mebook.navigation.MeBookScreens.ProfileRoute
import com.example.mebook.navigation.MeBookScreens.SearchRoute
import com.example.mebook.navigation.MeBookScreens.WriteRoute

sealed class BottomNavItem(
    var title: String,
    var icon: ImageVector,
    var screen_route: String
) {
    object Home : BottomNavItem("Home", Icons.Default.Home, HomeRoute.route)
    object Write : BottomNavItem("Write", Icons.Default.Add, WriteRoute.route)
    object Search : BottomNavItem("Search", Icons.Default.Search, SearchRoute.route)
    object Profile : BottomNavItem("Profile", Icons.Default.Person, ProfileRoute.route)
}