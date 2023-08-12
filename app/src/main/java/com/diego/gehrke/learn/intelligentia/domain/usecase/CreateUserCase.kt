package com.diego.gehrke.learn.intelligentia.domain.usecase

import com.diego.gehrke.learn.intelligentia.domain.model.Result
import com.diego.gehrke.learn.intelligentia.domain.repository.UserRepositoryImpl
import javax.inject.Inject

class CreateUserUseCase @Inject constructor(
    private val userRepositoryImpl: UserRepositoryImpl
) {
    suspend fun execute(email: String, password: String): Result<Unit> {
        return userRepositoryImpl.createUserWithEmailAndPassword(email, password)
    }
}