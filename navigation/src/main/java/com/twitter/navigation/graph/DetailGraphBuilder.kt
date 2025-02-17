package com.twitter.navigation.graph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.twitter.domain.models.TweetEntity
import com.twitter.navigation.screens.Detail

@Immutable
data class DetailScreens(
    val detailMain: @Composable (TweetEntity) -> Unit,
)

internal fun NavGraphBuilder.detailGraph(
    screens: DetailScreens
) {
    navigation(
        startDestination = DetailGraph.startDestination,
        route = DetailGraph.route
    ) {
        composable(DetailGraph.detailMain.route , DetailGraph.detailMain.arguments) {
            val id = Detail.objectParser(it)
            screens.detailMain(id)
        }
    }
}

