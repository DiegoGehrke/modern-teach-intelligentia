package com.diego.gehrke.learn.intelligentia.model

import java.util.Date

data class ConversationFirestoreModel(
    var id: String = Date().time.toString(),
    var title: String = "",
    var createdAt: Date = Date()
)



