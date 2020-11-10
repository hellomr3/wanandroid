package com.looptry.wanandroid.di

import com.looptry.wanandroid.app.LoginManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
@InstallIn(ApplicationComponent::class)
@Module
object AppModule {

    @Provides
    fun provideLoginManager(): LoginManager {
        return LoginManager()
    }
}