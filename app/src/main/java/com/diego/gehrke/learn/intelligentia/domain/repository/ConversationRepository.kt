package com.diego.gehrke.learn.intelligentia.domain.repository

import com.diego.gehrke.learn.intelligentia.model.ConversationFirestoreModel

interface ConversationRepository {
    suspend fun fetchConversations() : MutableList<ConversationFirestoreModel>
    fun newConversation(conversation: ConversationFirestoreModel) : ConversationFirestoreModel
    suspend fun deleteConversation(conversationId: String)
}