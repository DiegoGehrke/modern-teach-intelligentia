package com.diego.gehrke.learn.intelligentia.conversation

import androidx.compose.runtime.toMutableStateList
import com.diego.gehrke.learn.intelligentia.model.Message

class ConversationUiState(
    val channelName: String,
    val channelMembers: Int,
    initialMessages: List<Message>
) {
    private val _messages: MutableList<Message> = initialMessages.toMutableStateList()
    val messages: List<Message> = _messages

    fun addMessage(msg: Message) {
        _messages.add(0, msg) // Add to the beginning of the list
    }
}