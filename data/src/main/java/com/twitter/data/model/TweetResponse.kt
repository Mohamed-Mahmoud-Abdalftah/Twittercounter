package com.twitter.data.model

import com.google.gson.annotations.SerializedName

data class TweetResponse(
    @SerializedName("data")
    val `data`: Data
)

data class Data(
    @SerializedName("edit_history_tweet_ids")
    val editHistoryTweetIds: List<String>,
    @SerializedName("text")
    val text: String,
    @SerializedName("id")
    val id: String
)