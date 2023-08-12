package com.diego.gehrke.learn.intelligentia.data.repository

import com.diego.gehrke.learn.intelligentia.domain.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import com.diego.gehrke.learn.intelligentia.domain.model.Result

class FirebaseUserRepository : UserRepository {
    override suspend fun createUserWithEmailAndPassword(email: String, password: String): Result<Unit> {
        return try {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).await()
            Result.Success
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}