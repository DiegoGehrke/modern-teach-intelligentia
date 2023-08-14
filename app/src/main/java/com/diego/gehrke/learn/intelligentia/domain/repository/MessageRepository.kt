package com.diego.gehrke.learn.intelligentia.domain.repository

import com.diego.gehrke.learn.intelligentia.domain.model.MessageModel
import kotlinx.coroutines.flow.Flow

interface MessageRepository {
    fun fetchMessages(conversationId: String): Flow<List<MessageModel>>
    fun createMessage(message: MessageModel): MessageModel
    fun deleteMessage()
}