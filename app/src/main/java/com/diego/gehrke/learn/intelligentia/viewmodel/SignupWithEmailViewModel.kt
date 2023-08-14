package com.diego.gehrke.learn.intelligentia.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diego.gehrke.learn.intelligentia.domain.model.Result
import com.diego.gehrke.learn.intelligentia.domain.usecase.CreateUserUseCase
import com.diego.gehrke.learn.intelligentia.utilities.EmailValidator
import com.diego.gehrke.learn.intelligentia.utilities.NameValidator
import com.diego.gehrke.learn.intelligentia.utilities.PasswordValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupWithEmailViewModel @Inject constructor(
    private val createUserUseCase: CreateUserUseCase,
) : ViewModel() {
    private val _email = MutableStateFlow("")

    private val _isValidEmail = MutableStateFlow(false)
    val isValidEmail: StateFlow<Boolean> get() = _isValidEmail

    private val _password = MutableStateFlow("")

    private val _isValidPassword = MutableStateFlow(false)
    val isValidPassword: StateFlow<Boolean> get() = _isValidPassword

    private val _name = MutableStateFlow("")
    val name: StateFlow<String> get() = _name

    private val _isValidName = MutableStateFlow(false)
    val isValidName: StateFlow<Boolean> get() = _isValidName

    private val _createUserResult = MutableStateFlow<Result<Unit>?>(null)
    val createUserResult: StateFlow<Result<Unit>?> get() = _createUserResult


    private val _loadingState = MutableStateFlow(false)
    val loadingState: StateFlow<Boolean> get() = _loadingState

    fun onEmailChanged(email: String) {
        _email.value = email

        val isValidEmail = EmailValidator.isValidEmail(email)
        _isValidEmail.value = isValidEmail
    }

    fun onPasswordChanged(password: String) {
        _password.value = password

        val isValidPassword = PasswordValidator.isValidPassword(password)
        _isValidPassword.value = isValidPassword
    }

    fun onNameChanged(name: String) {
        _name.value = name

        val isValidName = NameValidator.isValidName(name)
        _isValidName.value = isValidName
    }

    fun createUserWithEmailAndPassword() {
        viewModelScope.launch {
            try {
                _loadingState.value = true
                _createUserResult.value = createUserUseCase.execute(_email.value, _password.value)
            } catch (e: Exception) {
                _createUserResult.value = Result.Error(e)
            } finally {
                _loadingState.value = false
            }
        }
    }
}
