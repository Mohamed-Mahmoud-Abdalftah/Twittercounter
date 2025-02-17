package com.twitter.data.di

import com.twitter.data.datasource.TweetDataSource
import com.twitter.data.datasource.TweetDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun provideCharacterByTypeDataSource(remoteDS: TweetDataSourceImpl): TweetDataSource

}
