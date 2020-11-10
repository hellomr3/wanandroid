package com.looptry.wanandroid.di

import com.blankj.utilcode.util.GsonUtils
import com.google.gson.GsonBuilder
import com.looptry.architecture.net.gson.GsonTypeAdapterFactory
import com.looptry.wanandroid.config.NetConfig
import com.looptry.wanandroid.net.RequestApi
import com.looptry.wanandroid.net.UserApi
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
object NetworkModule {

    @NoAuthClient
    @Provides
    fun provideNoAuthClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(LogIntercept.getHttpLogInterceptor())
            .build()
    }

    @AuthClient
    @Provides
    fun provideAuthClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(LogIntercept.getHttpLogInterceptor())
            .build()
    }

    @NoAuthRetrofit
    @Provides
    fun provideNoAuthRetrofit(
        @NoAuthClient client: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(NetConfig.HTTP_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
    }

    @AuthRetrofit
    @Provides
    fun provideAuthRetrofit(
        @AuthClient client: OkHttpClient
    ): Retrofit {

        return Retrofit.Builder()
            .baseUrl(NetConfig.HTTP_URL)
            .addConverterFactory(
                GsonConverterFactory.create(
//                    GsonBuilder().registerTypeAdapterFactory(
//                        GsonTypeAdapterFactory()
//                    ).create()
                )
            )
            .addConverterFactory(ScalarsConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    fun provideService(
        @NoAuthRetrofit retrofit: Retrofit
    ): RequestApi {
        return retrofit.create(RequestApi::class.java)
    }

    @Provides
    fun provideUserService(
        @AuthRetrofit retrofit: Retrofit
    ): UserApi {
        return retrofit.create(UserApi::class.java)
    }
}