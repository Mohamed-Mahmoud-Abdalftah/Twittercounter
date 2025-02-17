package com.twitter.home.presentation

import androidx.lifecycle.viewModelScope
import com.twitter.core.navigation.NavigationService
import com.twitter.core.presentation.StateAndEventViewModel
import com.twitter.domain.useCase.TweetUseCase
import com.twitter.domain.useCase.TwitterCountUseCase
import com.twitter.home.presentation.state.TweetUIState
import com.twitter.home.presentation.uievent.TweetUIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val tweetUseCase: TweetUseCase,
    private val countUseCase: TwitterCountUseCase,
    private val navigator: NavigationService
) : StateAndEventViewModel<TweetUIState, TweetUIEvent>(TweetUIState.InitialLoad) {

    init {
        viewModelScope.launch {
            events.collect { event ->
                handleEvent(event)
            }
        }
    }

    // Handle incoming events
    fun handleEvent(event: TweetUIEvent) {
        when (event) {
            is TweetUIEvent.OnTextChanged -> handleTextChanged(event.text)
            is TweetUIEvent.OnClearText -> handleClearText()
            is TweetUIEvent.OnPostTweet -> handlePostTweet()
            is TweetUIEvent.OnDismiss -> handleBack()
        }
    }

    // Handle text input change
    private fun handleTextChanged(text: String) {
        val charactersTyped = countUseCase.calculateCharacterCount(text)
        val charactersRemaining = countUseCase.calculateRemainingCharacters(text)
        val isTweetReady = countUseCase.isTweetValid(text)

        if (charactersRemaining < 0) {
            return
        }

        updateUiState {
            TweetUIState.Loaded(
                tweetText = text,
                charactersTyped = charactersTyped,
                charactersRemaining = charactersRemaining,
                isTweetReady = isTweetReady
            )
        }
    }


    // Clear tweet text
    private fun handleClearText() {
        updateUiState {
            TweetUIState.Loaded(
                tweetText = "",
                charactersTyped = 0,
                charactersRemaining = 280,
                isTweetReady = false
            )
        }
    }

    // Post the tweet
    private fun handlePostTweet() {
        val currentState = uiState.value as? TweetUIState.Loaded ?: return
        val tweetText = currentState.tweetText

        if (countUseCase.isTweetValid(tweetText)) {
            postTweet(tweetText)
        } else {
            showError("Tweet text is invalid!")
        }
    }

    // Post tweet logic
    private fun postTweet(text: String) {
        updateUiState { TweetUIState.Loading }

        viewModelScope.launch {
            try {
                val tweetData = tweetUseCase.execute(text)
                updateUiState { TweetUIState.TweetData(tweetEntity = tweetData) }
            } catch (e: Exception) {
                showError(e.message ?: "Failed to post tweet")
            }
        }
    }

    // Navigate back
    private fun handleBack() {
        navigator.goBack()
    }

    // Show error message
    private fun showError(message: String) {
        updateUiState { TweetUIState.Error(Throwable(message)) }
    }
}