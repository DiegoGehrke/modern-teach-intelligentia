package com.diego.gehrke.learn.intelligentia.utilities

object PasswordValidator {
    fun isValidPassword(password: String): Boolean {
        var haveOnlySpaces = true

        val haveMoreThenSevenCharacters: Boolean = password.length > 7

        for (char in password) {
            if (char != ' ') {
                haveOnlySpaces = false
                break
            }
        }

        return !haveOnlySpaces && haveMoreThenSevenCharacters
    }
}

