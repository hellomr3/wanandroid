package com.looptry.db.entity.user

import androidx.room.*
import com.looptry.db.entity.view.UserWithLibrary

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
@Dao
interface UserInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createOrReplace(info: UserInfo): Long

    @Delete(entity = UserInfo::class)
    suspend fun deleteById(id: UserInfoById)

    @Query("Select * from USERINFO")
    suspend fun query(): List<UserInfo>

    @Query("Select * from UserWithLibrary")
    suspend fun queryAll(): List<UserWithLibrary>
}