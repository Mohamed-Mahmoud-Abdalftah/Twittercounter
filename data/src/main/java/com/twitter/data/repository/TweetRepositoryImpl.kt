package com.twitter.data.repository

import com.twitter.data.datasource.TweetDataSource
import com.twitter.data.mapper.toDomain
import com.twitter.domain.models.TweetEntity
import com.twitter.domain.repositories.TweetRepository
import javax.inject.Inject

class TweetRepositoryImpl @Inject constructor(
    private val dataSource: TweetDataSource,
) : TweetRepository {

    override suspend fun sendTweet(text: String): TweetEntity {
        return try {
            dataSource.sendTweet(text).toDomain()
        } catch (exception: Exception) {
            throw Exception("Failed to load movie details", exception)
        }
    }
}

