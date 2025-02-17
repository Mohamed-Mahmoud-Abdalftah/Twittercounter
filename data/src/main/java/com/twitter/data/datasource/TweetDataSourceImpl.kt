package com.twitter.data.datasource


import com.twitter.core.utils.extensions.handleCall
import com.twitter.data.model.TweetRequest
import com.twitter.data.model.TweetResponse
import com.twitter.data.remote.ApiService
import javax.inject.Inject

class TweetDataSourceImpl @Inject constructor(
    private val api: ApiService
) : TweetDataSource {

    override suspend fun sendTweet(text: String): TweetResponse {
        return handleCall {
            api.sendTweet(TweetRequest(text))
        }
    }


}

