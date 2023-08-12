package com.diego.gehrke.learn.intelligentia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.diego.gehrke.learn.intelligentia.ui.navigation.BottomNavBarGraph
import com.diego.gehrke.learn.intelligentia.ui.theme.AppTheme
import com.diego.gehrke.learn.intelligentia.viewmodel.ChatGptViewModel
import com.diego.gehrke.learn.intelligentia.viewmodel.SettingsScreenViewModel
import com.diego.gehrke.learn.intelligentia.viewmodel.SignupWithEmailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val signupWithEmailViewModel by viewModels<SignupWithEmailViewModel>()
    private val settingsScreenViewModel by viewModels<SettingsScreenViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val chatGptViewModel = viewModel<ChatGptViewModel>()
            AppTheme() {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    BottomNavBarGraph(navController = navController, settingsScreenViewModel, chatGptViewModel)
                   /* NavigationGraph(
                        navController = navController,
                        signupWithEmailViewModel,
                        settingsScreenViewModel
                    )*/
                }
            }
        }
    }
}
