package com.looptry.wanandroid.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.looptry.architecture.application.BasicApplication
import com.looptry.wanandroid.widget.viewmodel.ShareViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
@InstallIn(ApplicationComponent::class)
@Module
object ViewModelModule {

    @Provides
    fun provideShareViewModel(
        @ApplicationContext context: Context
    ): ShareViewModel {
        require(context is BasicApplication)
        val viewModelStoreOwner = context.viewModelStore
        val factory = context.mViewModelFactory
        return ViewModelProvider(
            viewModelStoreOwner,
            factory
        ).get(
            ShareViewModel::class.java
        )
    }
}