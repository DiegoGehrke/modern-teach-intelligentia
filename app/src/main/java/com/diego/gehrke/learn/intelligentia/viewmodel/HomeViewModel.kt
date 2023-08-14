package com.diego.gehrke.learn.intelligentia.viewmodel

import com.diego.gehrke.learn.intelligentia.data.local.UserPreferences
import com.diego.gehrke.learn.intelligentia.viewmodel.baseviewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    userPreferences: UserPreferences
) : BaseViewModel(userPreferences) {

}