package com.looptry.db.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.looptry.db.converters.Converters
import com.looptry.db.entity.library.Library
import com.looptry.db.entity.library.LibraryDao
import com.looptry.db.entity.user.UserInfo
import com.looptry.db.entity.user.UserInfoDao
import com.looptry.db.entity.view.UserWithLibrary

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
@Database(
    entities = [UserInfo::class, Library::class],
    views = [UserWithLibrary::class],
    version = 3
)
@TypeConverters(Converters::class)
abstract class AppDb : RoomDatabase() {
    abstract fun getUserInfoDao(): UserInfoDao
    abstract fun getLibraryDao(): LibraryDao
}