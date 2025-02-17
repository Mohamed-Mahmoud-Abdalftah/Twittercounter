package com.twitter.navigation.screens

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.twitter.domain.models.TweetEntity
import com.twitter.navigation.utils.ArgsScreen
import com.twitter.navigation.utils.DestinationRoute
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.collections.List

object Detail : ArgsScreen<TweetEntity> {
    override val route: String = "detail/{character}"
    override val arguments: List<NamedNavArgument> =
        listOf(navArgument("character") { type = NavType.StringType })

    override fun objectParser(entry: NavBackStackEntry): TweetEntity {
        return Json.decodeFromString("")
    }

    override fun destination(arg: TweetEntity): DestinationRoute {
        val json = Json.encodeToString(arg)
        return "detail/$json"
    }
}
