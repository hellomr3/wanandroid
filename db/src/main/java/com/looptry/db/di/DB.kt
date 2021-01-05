package com.looptry.db.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.looptry.db.db.AppDb
import com.looptry.db.entity.library.LibraryDao
import com.looptry.db.entity.user.UserInfoDao
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
@InstallIn(ApplicationComponent::class)
@Module
object DB {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDb {
        return Room.databaseBuilder(
            context,
            AppDb::class.java,
            "wanandroid"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideUserInfoDao(
        db: AppDb
    ): UserInfoDao {
        return db.getUserInfoDao()
    }

    @Provides
    @Singleton
    fun provideLibraryDao(db: AppDb): LibraryDao {
        return db.getLibraryDao()
    }
}