package com.diego.gehrke.learn.intelligentia.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiRequest(
    val tokenId: String
)