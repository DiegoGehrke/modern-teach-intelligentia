package com.diego.gehrke.learn.intelligentia.domain.model

sealed class Result<out T> {
    object Success : Result<Unit>()
    data class Error(val exception: Exception) : Result<Nothing>()
    object Loading : Result<Unit>()
}
