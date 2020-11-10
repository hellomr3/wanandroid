package com.looptry.wanandroid.di

import javax.inject.Qualifier

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NoAuthClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthClient