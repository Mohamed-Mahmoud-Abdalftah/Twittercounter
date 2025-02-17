package com.twitter.home.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import com.twitter.home.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar() {
    CenterAlignedTopAppBar( title = {
        TitleText()
    }, actions = {
        BackAction()
    })
}


@Composable
fun TitleText() {
    Text(
        text = "Twitter character count",
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.titleMedium
    )
}


@Composable
fun BackAction() {
    IconButton(onClick = { }) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_back),
            contentDescription = "Back"
        )
    }
}



