package com.diego.gehrke.learn.intelligentia

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.diego.gehrke.learn.intelligentia.ui.navigation.BottomNavBarGraph
import com.diego.gehrke.learn.intelligentia.ui.theme.ApplyTheme
import com.diego.gehrke.learn.intelligentia.viewmodel.ChatGptViewModel
import com.diego.gehrke.learn.intelligentia.viewmodel.HomeViewModel
import com.diego.gehrke.learn.intelligentia.viewmodel.SettingsScreenViewModel
import com.diego.gehrke.learn.intelligentia.viewmodel.SignupWithEmailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val signupWithEmailViewModel by viewModels<SignupWithEmailViewModel>()

    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val chatGptViewModel = viewModel<ChatGptViewModel>()
            val settingsScreenViewModel = viewModel<SettingsScreenViewModel>()
            val homeViewModel = viewModel<HomeViewModel>()
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = {
                        ApplyTheme(settingsScreenViewModel.isDarkModeEnabled.isInitialized) {
                            BottomNavBarGraph(
                                navController = navController,
                                settingsScreenViewModel,
                                chatGptViewModel,
                                homeViewModel
                            )
                        }
                    }
                ) {

                    /* NavigationGraph(
                        navController = navController,
                        signupWithEmailViewModel,
                        settingsScreenViewModel
                    )*/

                }
        }
    }
}
