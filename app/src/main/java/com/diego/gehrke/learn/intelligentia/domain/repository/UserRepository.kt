package com.diego.gehrke.learn.intelligentia.domain.repository

import com.diego.gehrke.learn.intelligentia.domain.model.Result

interface UserRepository {
    suspend fun createUserWithEmailAndPassword(email: String, password: String): Result<Unit>
}
