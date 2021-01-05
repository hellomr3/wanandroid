package com.looptry.db.entity.view

import androidx.room.DatabaseView

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
@DatabaseView("SELECT user.id,user.name,user.age,library.id as libraryId,library.history from UserInfo as user Inner Join Library  On user.id == library.userId ")
data class UserWithLibrary(
    val id: Int,
    val name: String,
    val age: Int,
    val libraryId: Int,
    val history: String
)