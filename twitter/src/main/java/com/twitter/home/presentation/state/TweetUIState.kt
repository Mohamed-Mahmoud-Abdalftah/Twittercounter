package com.twitter.home.presentation.state

import androidx.compose.runtime.Immutable
import com.twitter.domain.models.TweetEntity


@Immutable
sealed class TweetUIState {
    data object Loading : TweetUIState()
    data object InitialLoad : TweetUIState()

    data class Loaded(
        val tweetText: String = "",
        val charactersTyped: Int = 0,
        val charactersRemaining: Int = 280,
        val isTweetReady: Boolean = false,
        val selectedCharacterDataID: Int? = null
    ) : TweetUIState()

    data class Error(
        val throwable: Throwable
    ) : TweetUIState()

    data class TweetData(
        val tweetEntity: TweetEntity? = null
    ) : TweetUIState()
}
