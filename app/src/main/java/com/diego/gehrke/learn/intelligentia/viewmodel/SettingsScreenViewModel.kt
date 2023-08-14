package com.diego.gehrke.learn.intelligentia.viewmodel

import com.diego.gehrke.learn.intelligentia.data.local.UserPreferences
import com.diego.gehrke.learn.intelligentia.viewmodel.baseviewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsScreenViewModel @Inject constructor(
    userPreferences: UserPreferences
) : BaseViewModel(userPreferences) {

    private val _languagesList: MutableList<String> = mutableListOf("En-Us", "Pt-Br")
    val languagesList: MutableList<String> get() = _languagesList

}