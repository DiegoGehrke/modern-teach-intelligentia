package com.diego.gehrke.learn.intelligentia.data.local

import android.content.SharedPreferences

class UserPreferencesImpl(
    private val sharedPreferences: SharedPreferences
) : UserPreferences {
    private val DARK_MODE_KEY = "dark_mode_key"

    override fun getDarkMode(): Boolean {
        return sharedPreferences.getBoolean(DARK_MODE_KEY, false)
    }

    override fun setDarkMode(isDarkMode: Boolean) {
        sharedPreferences.edit().putBoolean(DARK_MODE_KEY, isDarkMode).apply()
    }
}