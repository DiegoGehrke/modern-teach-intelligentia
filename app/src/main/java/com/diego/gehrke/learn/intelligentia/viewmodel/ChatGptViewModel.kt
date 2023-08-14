package com.diego.gehrke.learn.intelligentia.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aallam.openai.api.BetaOpenAI
import com.diego.gehrke.learn.intelligentia.conversation.ConversationUiState
import com.diego.gehrke.learn.intelligentia.conversation.OpenAIWrapper
import com.diego.gehrke.learn.intelligentia.data.remote.OpenAiApiBuilder
import com.diego.gehrke.learn.intelligentia.domain.model.MessageModel
import com.diego.gehrke.learn.intelligentia.domain.repository.ConversationRepository
import com.diego.gehrke.learn.intelligentia.domain.repository.MessageRepository
import com.diego.gehrke.learn.intelligentia.model.ConversationFirestoreModel
import com.diego.gehrke.learn.intelligentia.model.Message
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
@HiltViewModel
class ChatGptViewModel @Inject constructor(
    private val conversationRepo: ConversationRepository,
    private val messageRepo: MessageRepository,
    private val openAiApiBuilder: OpenAiApiBuilder
) : ViewModel() {
    private val _apiResponse: MutableStateFlow<String?> = MutableStateFlow(null)
    val apiResponse: StateFlow<String?> get() = _apiResponse.asStateFlow()

    private var openAIWrapper = OpenAIWrapper()
    var botIsTyping by mutableStateOf(false)
        private set

    var uiState = ConversationUiState(
        initialMessages = listOf(
            Message(
                author = "Intelli",
                content = "Welcome! I am Intelli, your smart friend. Let's learn english!",
                timestamp = "00:00 PM"
            )
        ), channelName = "#Intelli", channelMembers = 2
    )

    private val _currentConversation: MutableStateFlow<String> =
        MutableStateFlow(Date().time.toString())
    private val _conversations: MutableStateFlow<MutableList<ConversationFirestoreModel>> = MutableStateFlow(
        mutableListOf()
    )
    private val _messages: MutableStateFlow<HashMap<String, MutableList<MessageModel>>> =
        MutableStateFlow(HashMap())
    private val _isFetching: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val _isFabExpanded = MutableStateFlow(false)

    val currentConversationState: StateFlow<String> = _currentConversation.asStateFlow()
    val conversationsState: StateFlow<MutableList<ConversationFirestoreModel>> = _conversations.asStateFlow()
    val messagesState: StateFlow<HashMap<String, MutableList<MessageModel>>> =
        _messages.asStateFlow()
    val isFetching: StateFlow<Boolean> = _isFetching.asStateFlow()
    val isFabExpanded: StateFlow<Boolean> get() = _isFabExpanded

    private var stopReceivingResults = false

    @OptIn(BetaOpenAI::class)
    fun getApiResponse(request: String) {
        viewModelScope.launch {
            try {
                val response = openAiApiBuilder.sendMessageRequestToApi(request)
                val stringBuilder = StringBuilder()

                response.collect { chunk ->
                    val choices = chunk.choices

                    if (choices.isNotEmpty()) {
                        val completion = choices.last()
                        val delta = completion.delta

                        if (delta?.content != null) {
                            stringBuilder.append(delta.content.toString())
                            _apiResponse.value = stringBuilder.toString()
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e("OPEN API ERROR: ", e.message.toString())
            }
        }
    }

    fun makeRequestToChatGpt(userMessage: String) {
        viewModelScope.launch {
            getApiResponse(userMessage)
        }
    }

    fun onMessageSent(content: String) {
        // add user message to chat history
        addMessage("Diego", content)

        // start typing animation while request loads
        botIsTyping = true

        // fetch openai response and add to chat history
        viewModelScope.launch {
            val chatResponse = try {
                openAIWrapper.chat(content)
            } catch (e: Exception) {
                "Sorry, there was an error processing your request: ${e.message}"
            }

            botIsTyping = false
            addMessage(author = "Intelli", content = chatResponse)
        }
        stopReceivingResults = false

        val newMessageModel: MessageModel = MessageModel(
            question = content,
            answer = "Let me thinking...",
            conversationId = _currentConversation.value
        )

        val currentListMessage: MutableList<MessageModel> =
            getMessagesByConversation(_currentConversation.value).toMutableList()

        // Insert message to list
        currentListMessage.add(0, newMessageModel)
        setMessages(currentListMessage)


        var answerFromGPT: String = ""
        // When flow collecting updateLocalAnswer including FAB behavior expanded.
        // On completion FAB == false


        // Save to Firestore
        messageRepo.createMessage(newMessageModel.copy(answer = answerFromGPT))
    }
    private fun getMessagesByConversation(conversationId: String): MutableList<MessageModel> {
        if (_messages.value[conversationId] == null) return mutableListOf()

        val messagesMap: HashMap<String, MutableList<MessageModel>> =
            _messages.value.clone() as HashMap<String, MutableList<MessageModel>>

        return messagesMap[conversationId]!!
    }
    private fun addMessage(author: String, content: String, imageUrl: String? = null) {
        // calculate message timestamp
        val currentTime = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("h:mm a", Locale.getDefault())
        val timeNow = dateFormat.format(currentTime)

        val message = Message(
            author = author, content = content, timestamp = timeNow, imageUrl = imageUrl
        )

        uiState.addMessage(message)
    }

    private suspend fun fetchMessages() {
        if (_currentConversation.value.isEmpty() ||
            _messages.value[_currentConversation.value] != null) return

        val flow: Flow<List<MessageModel>> = messageRepo.fetchMessages(_currentConversation.value)

        flow.collectLatest {
            setMessages(it.toMutableList())
        }
    }

    private fun updateLocalAnswer(answer: String) {
        val currentListMessage: MutableList<MessageModel> =
            getMessagesByConversation(_currentConversation.value).toMutableList()

        currentListMessage[0] = currentListMessage[0].copy(answer = answer)

        setMessages(currentListMessage)
    }
    private fun setMessages(messages: MutableList<MessageModel>) {
        val messagesMap: HashMap<String, MutableList<MessageModel>> =
            _messages.value.clone() as HashMap<String, MutableList<MessageModel>>

        messagesMap[_currentConversation.value] = messages

        _messages.value = messagesMap
    }
}