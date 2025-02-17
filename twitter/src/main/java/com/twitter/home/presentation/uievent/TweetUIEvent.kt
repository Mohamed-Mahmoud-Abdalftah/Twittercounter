package com.twitter.home.presentation.uievent



sealed class TweetUIEvent {
    data class OnTextChanged(val text: String) : TweetUIEvent()
    data object OnClearText : TweetUIEvent()
    data object OnPostTweet : TweetUIEvent()
    data object OnDismiss : TweetUIEvent()
}
