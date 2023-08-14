package com.diego.gehrke.learn.intelligentia.conversation

import com.aallam.openai.api.BetaOpenAI
import com.aallam.openai.api.chat.ChatCompletion
import com.aallam.openai.api.chat.ChatCompletionRequest
import com.aallam.openai.api.chat.ChatMessage
import com.aallam.openai.api.chat.ChatRole
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.OpenAI
import com.diego.gehrke.learn.intelligentia.constants.ChatApiKey
import com.diego.gehrke.learn.intelligentia.utilities.AppConfiguration

@OptIn(BetaOpenAI::class)
class OpenAIWrapper {
    private val openAIToken: String = ChatApiKey.API_KEY
    private var conversation: MutableList<ChatMessage>
    private var openAI: OpenAI = OpenAI(openAIToken)
    private val botDefaultLanguage = AppConfiguration.getAppLanguage()

    init {
        conversation = mutableListOf(
            ChatMessage(
                role = ChatRole.System,
                content = ("You are a chat bot called Intelli, act like a teacher." +
                            "Always reply on $botDefaultLanguage." +
                            "Your answers will be short and concise, since they will be required to fit on " +
                            "a mobile device display. You will also answer user questions about English. " +
                                    "Explain word differences using examples in sentences. Explain the " +
                                    "difference between two similar words/phrases in english. Help with " +
                                    "English grammar questions. Provide synonyms and antonyms. Help to " +
                                    "expand user vocabulary in english by suggesting words related to " +
                                    "a specific topic. If the user writes in English and there are " +
                                    "writing errors, correct it and then reply to his message. " +
                                    "You can chat with the user in English. " +
                                    "Translate word/phrase/sentence from the user language to english. " +
                                    "You can play games with the user according to the user's rules.").trimMargin()
            )
        )
    }

    suspend fun chat(message: String): String {
        // add the user's message to the chat history
        conversation.add(
            ChatMessage(
                role = ChatRole.User,
                content = message
            )
        )

        // build the OpenAI network request
        val chatCompletionRequest = ChatCompletionRequest(
            model = ModelId("gpt-3.5-turbo"),
            messages = conversation
        )
        val completion: ChatCompletion = openAI.chatCompletion(chatCompletionRequest)

        // extract the response to show in the app
        val chatResponse = completion.choices[0].message?.content ?: ""

        // add the response to the conversation history
        conversation.add(
            ChatMessage(
                role = ChatRole.Assistant,
                content = chatResponse
            )
        )

        return chatResponse
    }
}