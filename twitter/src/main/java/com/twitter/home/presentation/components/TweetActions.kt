package com.twitter.home.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.twitter.home.R
import com.twitter.home.presentation.state.TweetUIState

@Composable
fun TweetActions(
    tweetUIState: TweetUIState,
    onCopyText: () -> Unit,
    onClearText: () -> Unit,
    onPostTweet: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        // Copy Text Button
        ActionButton(
            text = "Copy Text",
            onClick = onCopyText,
            color = MaterialTheme.colorScheme.tertiary,
            modifier = Modifier.padding(end = 8.dp)
        )

        // Clear Text Button
        ActionButton(
            text = "Clear Text",
            onClick = onClearText,
            color = MaterialTheme.colorScheme.error,
            modifier = Modifier.padding(start = 8.dp)
        )
    }

    // Post Tweet Button
    PostTweetButton(
        onClick = onPostTweet,
        isEnabled = (tweetUIState as? TweetUIState.Loaded)?.isTweetReady ?: false
    )
}

@Composable
fun ActionButton(
    text: String,
    onClick: () -> Unit,
    color: Color,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(color),
        modifier = modifier,
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(text, color = Color.White, style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
fun PostTweetButton(
    onClick: () -> Unit,
    isEnabled: Boolean
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(16.dp),
        enabled = isEnabled,
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
    ) {
        Text(stringResource(R.string.post_tweet), color = Color.White, style = MaterialTheme.typography.titleLarge)
    }
}
