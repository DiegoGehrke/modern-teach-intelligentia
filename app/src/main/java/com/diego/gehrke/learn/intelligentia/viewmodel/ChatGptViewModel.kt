package com.diego.gehrke.learn.intelligentia.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aallam.openai.api.BetaOpenAI
import com.diego.gehrke.learn.intelligentia.data.remote.OpenAiApiBuilder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatGptViewModel @Inject constructor(
    private val openAiApiBuilder: OpenAiApiBuilder
) : ViewModel() {
        private val _apiResponse: MutableStateFlow<String?> = MutableStateFlow(null)
        val apiResponse: StateFlow<String?> get() = _apiResponse.asStateFlow()

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
}