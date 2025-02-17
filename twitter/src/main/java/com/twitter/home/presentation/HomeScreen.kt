package com.twitter.home.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.twitter.home.R
import com.twitter.home.presentation.components.Toolbar
import com.twitter.home.presentation.components.TweetActions
import com.twitter.home.presentation.components.TweetCounters
import com.twitter.home.presentation.components.TweetInputField
import com.twitter.home.presentation.state.TweetUIState
import com.twitter.home.presentation.uievent.TweetUIEvent


@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = hiltViewModel()
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val keyboardController = LocalSoftwareKeyboardController.current
    val clipboardManager: ClipboardManager = LocalClipboardManager.current

    Column {
        Toolbar()

        Box(modifier = Modifier.imePadding().padding(top = 10.dp)) {
            if (state is TweetUIState.Loading) {
                LoadingIndicator()
            }

            TwitterContent(
                state = state,
                onTweetTextChanged = { viewModel.onEvent(TweetUIEvent.OnTextChanged(it)) },
                onCopyText = {
                    clipboardManager.setText(
                        AnnotatedString(
                            (state as? TweetUIState.Loaded)?.tweetText ?: ""
                        )
                    )
                },
                onClearText = { viewModel.handleEvent(TweetUIEvent.OnClearText) },
                onPostTweet = {
                    viewModel.handleEvent(TweetUIEvent.OnPostTweet)
                    keyboardController?.hide()
                }
            )
        }
    }

    BackHandler(enabled = true) {
        viewModel.onEvent(TweetUIEvent.OnDismiss)
    }
}

@Composable
fun LoadingIndicator() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TwitterContent(
    state: TweetUIState,
    onTweetTextChanged: (String) -> Unit,
    onCopyText: () -> Unit,
    onClearText: () -> Unit,
    onPostTweet: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Twitter logo below the app bar, centered
        Icon(
            painter = painterResource(id = R.drawable.logo),
            tint = null,
            contentDescription = "Twitter Icon",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        // Counters
        TweetCounters(state)

        // Tweet text field
        TweetInputField(state, onTweetTextChanged)

        // Action buttons row
        TweetActions(
            state,
            onCopyText = onCopyText,
            onClearText = onClearText,
            onPostTweet = onPostTweet
        )
    }
}


@Preview(showBackground = true)
@Composable
fun TwitterContentPreview() {
    Column {
        Toolbar()
        Box(modifier = Modifier.imePadding()) {


            TwitterContent(
                state = TweetUIState.Loaded(
                    tweetText = "Hello, Twitter! 🚀",
                    charactersTyped = 20,
                    charactersRemaining = 260,
                    isTweetReady = true
                ),
                onTweetTextChanged = {},
                onCopyText = {},
                onClearText = {},
                onPostTweet = {}
            )
        }
    }
}
