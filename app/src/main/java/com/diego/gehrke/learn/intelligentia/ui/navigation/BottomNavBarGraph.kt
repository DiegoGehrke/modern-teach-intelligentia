package com.diego.gehrke.learn.intelligentia.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.diego.gehrke.learn.intelligentia.conversation.ConversationContent
import com.diego.gehrke.learn.intelligentia.ui.daily.DailyGoalScreen
import com.diego.gehrke.learn.intelligentia.ui.home.HomeScreen
import com.diego.gehrke.learn.intelligentia.ui.profile.ProfileScreen
import com.diego.gehrke.learn.intelligentia.ui.settings.SettingsScreen
import com.diego.gehrke.learn.intelligentia.ui.theme.ApplyTheme
import com.diego.gehrke.learn.intelligentia.viewmodel.ChatGptViewModel
import com.diego.gehrke.learn.intelligentia.viewmodel.HomeViewModel
import com.diego.gehrke.learn.intelligentia.viewmodel.SettingsScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavBarGraph(
    navController: NavHostController,
    settingsScreenViewModel: SettingsScreenViewModel,
    chatGptViewModel: ChatGptViewModel,
    homeViewModel: HomeViewModel
) {
        Scaffold(
            bottomBar = {
                ApplyTheme(
                    isDarkModeEnabled = settingsScreenViewModel
                        .isDarkModeEnabled
                        .isInitialized
                ) {
                    BottomNavBar(navController = navController)
                }
            },
            modifier = Modifier
                .fillMaxSize(),
            contentColor = Color.White,
        ) { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                NavHost(
                    navController = navController,
                    startDestination = BottomNavBarRoutes.Home.route
                ) {
                    composable(route = BottomNavBarRoutes.Home.route) {
                        HomeScreen(navController, homeViewModel)
                    }
                    composable(route = BottomNavBarRoutes.AiChat.route) {
                        ConversationContent(
                            uiState = chatGptViewModel.uiState,
                            onMessageSent = chatGptViewModel::onMessageSent,
                            botIsTyping = chatGptViewModel.botIsTyping
                        )
                    }
                    composable(route = BottomNavBarRoutes.DailyGoal.route) {
                        DailyGoalScreen()
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

}
