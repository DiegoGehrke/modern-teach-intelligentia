package com.diego.gehrke.learn.intelligentia.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CalendarToday
import androidx.compose.material.icons.rounded.Chat
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavBarRoutes (
    val route: String,
    val title: String,
    val icon: ImageVector? = null
) {
    object Profile: BottomNavBarRoutes(
        route = "profile",
        title = "Profile",
        icon = Icons.Rounded.Person
    )

    object DailyGoal: BottomNavBarRoutes(
        route = "dailyGoal",
        title = "Daily Goal",
        icon = Icons.Rounded.CalendarToday
    )

    object AiChat: BottomNavBarRoutes(
        route = "aiChat",
        title = "AI Chat",
        icon = Icons.Rounded.Chat
    )

    object Home: BottomNavBarRoutes(
        route = "home",
        title = "Home",
        icon = Icons.Rounded.Home
    )
}