package com.twitter.data.remote

import com.twitter.data.model.TweetRequest
import com.twitter.data.model.TweetResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("tweets")
    suspend fun sendTweet(@Body tweet: TweetRequest): Response<TweetResponse>
}
