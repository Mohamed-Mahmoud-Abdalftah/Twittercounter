package com.twitter.navigation.graph

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import com.twitter.navigation.utils.ArgsScreen
import com.twitter.navigation.utils.DestinationRoute

object DetailMain : ArgsScreen<Unit> {
    override fun destination(arg: Unit): DestinationRoute = route
    override val route: String = "detailMain"
    override val arguments: List<NamedNavArgument> = emptyList()
    override fun objectParser(entry: NavBackStackEntry) {}
}