package com.diego.gehrke.learn.intelligentia.utilities

object AppConfiguration {
    private var appLanguage: String = "portuguese"
    private var chatBotDefaultLanguage = "portuguese"
    fun getAppLanguage(): String {
        return appLanguage
    }
    fun setAppLanguage(language: String) {
        appLanguage = language
    }
    fun resetConfiguration() {
        appLanguage = "portuguese"
    }
}
