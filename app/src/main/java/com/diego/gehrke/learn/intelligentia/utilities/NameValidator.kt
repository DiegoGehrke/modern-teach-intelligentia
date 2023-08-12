package com.diego.gehrke.learn.intelligentia.utilities

object NameValidator {
    fun isValidName(name: String): Boolean {
        var haveOnlySpaces = true

        val haveMoreThenTwoCharacters: Boolean = name.length > 2

        for (char in name) {
            if (char != ' ') {
                haveOnlySpaces = false
                break
            }
        }

        return !haveOnlySpaces && haveMoreThenTwoCharacters
    }
}