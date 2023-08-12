package com.diego.gehrke.learn.intelligentia.domain.repository

import com.diego.gehrke.learn.intelligentia.domain.model.Result
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor() : UserRepository {
    override suspend fun createUserWithEmailAndPassword(email: String, password: String): Result<Unit> {
        return try {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            Result.Success
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}
