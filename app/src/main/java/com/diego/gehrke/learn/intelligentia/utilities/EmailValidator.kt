package com.diego.gehrke.learn.intelligentia.utilities

import android.util.Patterns

object EmailValidator {
    fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
