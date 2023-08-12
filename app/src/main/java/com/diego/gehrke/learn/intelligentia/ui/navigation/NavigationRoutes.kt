package com.diego.gehrke.learn.intelligentia.ui.navigation

sealed class NavigationRoutes(val route: String) {

    object Welcome: NavigationRoutes(route = "welcome")
    object SetEmail: NavigationRoutes(route = "setEmail")
    object SetPassword: NavigationRoutes(route = "setPassword")
    object SetUsername: NavigationRoutes(route = "setUsername")
    object Home: NavigationRoutes(route = "home")
    object Settings: NavigationRoutes(route = "settings")
    object Profile: NavigationRoutes(route = "profile")
}