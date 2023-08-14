package com.diego.gehrke.learn.intelligentia.viewmodel.baseviewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.diego.gehrke.learn.intelligentia.data.local.UserPreferences

open class BaseViewModel(private val userPreferences: UserPreferences) : ViewModel() {
    private val _isDarkModeEnabled = MutableLiveData<Boolean>()
    val isDarkModeEnabled: LiveData<Boolean> get() = _isDarkModeEnabled

    init {
        _isDarkModeEnabled.value = userPreferences.getDarkMode()
    }

    fun updateDarkModeEnabled(isEnabled: Boolean) {
        _isDarkModeEnabled.value = isEnabled
        userPreferences.setDarkMode(isEnabled)
    }
}