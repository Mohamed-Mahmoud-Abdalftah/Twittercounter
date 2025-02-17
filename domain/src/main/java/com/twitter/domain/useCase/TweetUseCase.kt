package com.twitter.domain.useCase


import com.twitter.domain.models.TweetEntity
import com.twitter.domain.repositories.TweetRepository
import javax.inject.Inject


class TweetUseCase @Inject constructor(
    private val tweetRepository: TweetRepository,
) {

    suspend fun execute( text: String): TweetEntity {
        return tweetRepository.sendTweet(text)
    }

}
