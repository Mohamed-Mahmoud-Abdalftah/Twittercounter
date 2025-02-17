package com.twitter.data.datasource

import com.twitter.data.model.TweetResponse


interface TweetDataSource {
    suspend fun sendTweet(
        text: String
    ): TweetResponse

}
