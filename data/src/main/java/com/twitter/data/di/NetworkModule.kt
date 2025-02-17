package com.twitter.data.di

import android.os.Build
import androidx.annotation.RequiresApi
import com.twitter.core.utils.Constants
import com.twitter.core.utils.Constants.BASE_URL
import com.twitter.data.remote.ApiService
import com.twitter.data.remote.OAuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.Duration
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideOAuthInterceptor(): OAuthInterceptor {
        return OAuthInterceptor()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Provides
    @Singleton
    fun provideHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        oAuthInterceptor: OAuthInterceptor // Add OAuthInterceptor here
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(oAuthInterceptor) // Add the OAuthInterceptor
            .addNetworkInterceptor(loggingInterceptor)
            .connectTimeout(Duration.ofSeconds(10))
            .readTimeout(Duration.ofSeconds(30))
            .build()
    }


    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = if (Constants.DEVELOPMENT_MODE) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        return interceptor
    }

    @Singleton
    @Provides
    fun provideHomeApi(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}
