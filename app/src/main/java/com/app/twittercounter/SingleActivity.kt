package com.app.twittercounter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.app.twittercounter.ui.theme.TwitterTheme
import com.twitter.home.presentation.HomeScreen
import com.twitter.navigation.AppNavigation
import com.twitter.navigation.Navigator

import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SingleActivity : ComponentActivity() {

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       enableEdgeToEdge()
        setContent {
            TwitterTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    AppNavigation(
                        navigator = navigator,
                        homeScreen = {
                            HomeScreen()
                        },
                        detailScreen = {

                        },
                    )
                }
            }

        }
    }
}