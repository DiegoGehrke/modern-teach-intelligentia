package com.diego.gehrke.learn.intelligentia.model

data class CompletionRequest(
    val model: String,
    val prompt: String,
    val max_tokens: Int,
    val temperature: Float = 0f,
    val messages: List<String>
    )