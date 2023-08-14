package com.diego.gehrke.learn.intelligentia.data.remote

import android.content.ContentValues
import android.util.Log
import com.diego.gehrke.learn.intelligentia.constants.FirestoreCollectionName.conversationCollection
import com.diego.gehrke.learn.intelligentia.domain.repository.ConversationRepository
import com.diego.gehrke.learn.intelligentia.model.ConversationFirestoreModel
import com.diego.gehrke.learn.intelligentia.utilities.DataHolder
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ConversationRepositoryImpl @Inject constructor(
    private val fsInstance: FirebaseFirestore,
) : ConversationRepository {

    override suspend fun fetchConversations(): MutableList<ConversationFirestoreModel> {

        if (getFireBaseSnapShot().documents.isNotEmpty()) {
            val documents = getFireBaseSnapShot().documents

            return documents.map {
                it.toObject(ConversationFirestoreModel::class.java)
            }.toList() as MutableList<ConversationFirestoreModel>
        }

        return mutableListOf()
    }

    override fun newConversation(conversation: ConversationFirestoreModel): ConversationFirestoreModel {
        fsInstance.collection(conversationCollection).add(conversation)
        return conversation
    }

    override suspend fun deleteConversation(conversationId: String) {
        var desiredKey: String? = null

        getFireBaseSnapShot().documents.map { documentSnapshot ->
            val id = documentSnapshot.getString("id")
            if (id == conversationId) {
                desiredKey = documentSnapshot.id
            } else {
                null
            }
        }
        DataHolder.docPath = desiredKey.toString()

        val docRef = fsInstance
            .collection("conversations")
            .document(DataHolder.docPath)

        // Remove the 'capital' field from the document
        val updates = hashMapOf<String, Any>(
            "id" to FieldValue.delete(),
            "title" to FieldValue.delete(),
            "createdAt" to FieldValue.delete()
        )
        docRef.update(updates)
            .addOnSuccessListener {
                Log.d(
                    ContentValues.TAG,
                    "DocumentSnapshot successfully deleted from message!"
                )
            }
            .addOnFailureListener { e ->
                Log.w(
                    ContentValues.TAG,
                    "Error deleting document", e
                )
            }
    }
    private suspend fun getFireBaseSnapShot() =
        fsInstance.collection(conversationCollection)
            .orderBy("createdAt", Query.Direction.DESCENDING).get().await()

}