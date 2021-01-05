package com.looptry.db.entity.user

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
@Entity
data class UserInfo(
    @PrimaryKey
    val id: String,
    val name: String? = null,
    val age: Int? = null
)

data class UserInfoById(
    val id: String
)