package com.diego.gehrke.learn.intelligentia.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.diego.gehrke.learn.intelligentia.ui.chat.ChatScreen
import com.diego.gehrke.learn.intelligentia.ui.home.HomeScreen
import com.diego.gehrke.learn.intelligentia.ui.profile.ProfileScreen
import com.diego.gehrke.learn.intelligentia.ui.settings.SettingsScreen
import com.diego.gehrke.learn.intelligentia.viewmodel.ChatGptViewModel
import com.diego.gehrke.learn.intelligentia.viewmodel.HomeViewModel
import com.diego.gehrke.learn.intelligentia.viewmodel.SettingsScreenViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavBarGraph(
    navController: NavHostController,
    settingsScreenViewModel: SettingsScreenViewModel,
    chatGptViewModel: ChatGptViewModel
) {

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        contentColor = Color.White,
        bottomBar = { BottomNavBar(navController = navController) }
    ) {

        NavHost(
            navController = navController,
            startDestination = BottomNavBarRoutes.Home.route
        ) {
            composable(route = BottomNavBarRoutes.Home.route) {
                HomeScreen(navController, HomeViewModel())
            }
            composable(route = BottomNavBarRoutes.AiChat.route) {
                ChatScreen(chatGptViewModel)
            }
            composable(route = BottomNavBarRoutes.Dictionary.route) {

            }
            composable(route = BottomNavBarRoutes.Profile.route) {
                ProfileScreen(navController)
            }
            composable(route = NavigationRoutes.Settings.route) {
                SettingsScreen(settingsScreenViewModel, navController)
            }
        }

    }
}
