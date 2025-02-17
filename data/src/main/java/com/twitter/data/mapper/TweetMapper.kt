package com.twitter.data.mapper

import com.twitter.data.model.TweetResponse
import com.twitter.domain.models.TweetEntity


fun TweetResponse.toDomain(): TweetEntity {
    return TweetEntity(
        id = data.id,
        text = data.text,
        editHistoryIds = data.editHistoryTweetIds
    )
}
