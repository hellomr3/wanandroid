package com.looptry.wanandroid.di

import com.looptry.wanandroid.config.NetConfig
import com.looptry.wanandroid.net.RequestApi
import com.looptry.wanandroid.net.intercept.LogIntercept
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
@InstallIn(ApplicationComponent::class)
@Module
object NetModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(LogIntercept.getHttpLogInterceptor())
            .build()
    }

    @Provides
    fun provideRetrofit(
        client: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(NetConfig.HTTP_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
    }

    @Provides
    fun provideService(
        retrofit: Retrofit
    ): RequestApi {
        return retrofit.create(RequestApi::class.java)
    }
}