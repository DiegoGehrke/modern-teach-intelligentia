package com.diego.gehrke.learn.intelligentia.model

import com.diego.gehrke.learn.intelligentia.R

data class Message(
    val author: String,
    val content: String,
    val timestamp: String,
    val image: Int? = null,
    val authorImage: Int = if (author == "me") R.drawable.diego else R.drawable.diego,
    val imageUrl: String? = null
)