package com.twitter.domain.repositories

import com.twitter.domain.models.TweetEntity

interface TweetRepository {

   suspend fun sendTweet(text: String): TweetEntity

}

