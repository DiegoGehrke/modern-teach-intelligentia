package com.diego.gehrke.learn.intelligentia.data.local

interface UserPreferences {
    fun getDarkMode(): Boolean
    fun setDarkMode(isDarkMode: Boolean)
}