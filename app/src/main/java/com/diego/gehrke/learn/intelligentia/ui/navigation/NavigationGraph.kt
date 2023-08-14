package com.diego.gehrke.learn.intelligentia.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.diego.gehrke.learn.intelligentia.ui.home.HomeScreen
import com.diego.gehrke.learn.intelligentia.ui.profile.ProfileScreen
import com.diego.gehrke.learn.intelligentia.ui.settings.SettingsScreen
import com.diego.gehrke.learn.intelligentia.ui.signup.set_email.SetEmailScreen
import com.diego.gehrke.learn.intelligentia.ui.signup.set_password.SetPasswordScreen
import com.diego.gehrke.learn.intelligentia.ui.signup.set_username.SetUsernameScreen
import com.diego.gehrke.learn.intelligentia.ui.welcome.WelcomeScreen
import com.diego.gehrke.learn.intelligentia.viewmodel.HomeViewModel
import com.diego.gehrke.learn.intelligentia.viewmodel.SettingsScreenViewModel
import com.diego.gehrke.learn.intelligentia.viewmodel.SignupWithEmailViewModel

@Composable
fun NavigationGraph(
    navController: NavHostController,
    signupWithEmailViewModel: SignupWithEmailViewModel,
    settingsScreenViewModel: SettingsScreenViewModel,
    homeViewModel: HomeViewModel
) {
    NavHost(
        navController = navController,
        startDestination = NavigationRoutes.Settings.route
    ) {
        composable(route = NavigationRoutes.Welcome.route) {
            WelcomeScreen(navController)
        }
        composable(route = NavigationRoutes.SetEmail.route) {
            SetEmailScreen(navController, signupWithEmailViewModel)
        }
        composable(route = NavigationRoutes.SetPassword.route) {
            SetPasswordScreen(navController, signupWithEmailViewModel)
        }
        composable(route = NavigationRoutes.SetUsername.route) {
            SetUsernameScreen(navController, signupWithEmailViewModel)
        }
        composable(route = NavigationRoutes.Home.route) {
            HomeScreen(navController, homeViewModel)
        }
        composable(route = NavigationRoutes.Settings.route) {
            SettingsScreen(settingsScreenViewModel, navController)
        }
        composable(route = NavigationRoutes.Profile.route) {
            ProfileScreen(navController)
        }
    }
}