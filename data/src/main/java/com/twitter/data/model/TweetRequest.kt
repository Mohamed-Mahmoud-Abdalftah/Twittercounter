package com.twitter.data.model

import com.google.gson.annotations.SerializedName

data class TweetRequest(
    @SerializedName("text") val text: String? = null
)