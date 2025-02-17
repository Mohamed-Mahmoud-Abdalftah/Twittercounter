package com.twitter.domain.models

import kotlinx.serialization.Serializable


@Serializable
data class TweetEntity(
    val id: String,
    val text: String,
    val editHistoryIds: List<String>
): java.io.Serializable