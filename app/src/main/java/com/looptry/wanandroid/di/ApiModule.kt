package com.looptry.wanandroid.di

import com.looptry.wanandroid.repository.IRequest
import com.looptry.wanandroid.repository.IUser
import com.looptry.wanandroid.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
@Module
@InstallIn(ApplicationComponent::class)
abstract class ApiModule {
    @Binds
    @Singleton
    abstract fun bindIRequest(
        impl: Repository
    ): IRequest

    @Binds
    @Singleton
    abstract fun bindIUser(
        impl: Repository
    ): IUser
}