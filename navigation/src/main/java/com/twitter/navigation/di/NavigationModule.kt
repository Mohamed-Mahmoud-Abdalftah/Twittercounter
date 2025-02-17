package com.twitter.navigation.di

import com.twitter.core.navigation.NavigationService
import com.twitter.navigation.Navigator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NavigationModule {
    @Provides
    fun provideNavigationCommander(navigator: Navigator): NavigationService = navigator
}