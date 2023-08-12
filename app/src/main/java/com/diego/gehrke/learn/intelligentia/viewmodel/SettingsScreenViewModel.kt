package com.diego.gehrke.learn.intelligentia.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SettingsScreenViewModel() : ViewModel() {
    private val _languagesList: MutableList<String> = mutableListOf<String>("En-Us", "Pt-Br")
    val languagesList: MutableList<String> get() = _languagesList

    private val _isDarkTheme: MutableState<Boolean> = mutableStateOf(false)
    val isDarkTheme: MutableState<Boolean> get() = _isDarkTheme

    fun changeTheme() {
        _isDarkTheme.value = true
    }
}