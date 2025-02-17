package com.twitter.navigation.graph

import com.twitter.navigation.utils.NavigationGraph

object DetailGraph : NavigationGraph {
    override val route: String get() = "detailgraph"
    override val startDestination: String
        get() = detailMain.destination(Unit)

    val detailMain = DetailMain
}