package com.twitter.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.twitter.home.R
import com.twitter.home.presentation.state.TweetUIState

@Composable
fun TweetCounters(state: TweetUIState) {
    // Check if the state is Loaded before accessing the characters data

    val charactersTyped = (state as? TweetUIState.Loaded)?.charactersTyped ?: 0
    val charactersRemaining = (state as? TweetUIState.Loaded)?.charactersRemaining ?: 280

    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(8.dp))
                .border(
                    2.dp, MaterialTheme.colorScheme.secondary, shape = RoundedCornerShape(8.dp)
                )
        ) {
            Column(
                verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize()
            ) {
                // Title Section
                CounterTitle(stringResource(R.string.characters_typed))

                // Value Section
                CounterValue(stringResource(R.string._280, charactersTyped))
            }
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(8.dp))
                .border(
                    2.dp, MaterialTheme.colorScheme.secondary, shape = RoundedCornerShape(8.dp)
                )
        ) {
            Column(
                verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize()
            ) {
                // Title Section
                CounterTitle(stringResource(R.string.characters_remaining))

                // Value Section
                CounterValue(charactersRemaining.toString())
            }
        }
    }

}


@Composable
fun CounterTitle(title: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(35.dp)
            .background(MaterialTheme.colorScheme.secondary), contentAlignment = Alignment.Center
    ) {
        Text(
            title,
            color = MaterialTheme.colorScheme.tertiaryContainer,
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 14.sp
        )
    }
}

@Composable
fun CounterValue(value: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = value, style = MaterialTheme.typography.headlineLarge
        )
    }
}


