package com.diego.gehrke.learn.intelligentia.data.remote

interface FirestoreDataSource {
    suspend fun deleteData(dataId: String)
}