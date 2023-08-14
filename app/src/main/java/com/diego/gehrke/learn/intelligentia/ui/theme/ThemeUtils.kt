package com.diego.gehrke.learn.intelligentia.ui.theme

import androidx.compose.runtime.Composable

@Composable
fun ApplyTheme(isDarkModeEnabled: Boolean, content: @Composable () -> Unit) {
    AppTheme(
        useDarkTheme = isDarkModeEnabled,
        content = content
    )
}
