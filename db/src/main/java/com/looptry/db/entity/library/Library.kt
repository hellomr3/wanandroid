package com.looptry.db.entity.library

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.looptry.db.entity.user.UserInfo

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
@Entity(
    foreignKeys = [
        ForeignKey(
            entity = UserInfo::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.NO_ACTION,
            onUpdate = ForeignKey.NO_ACTION
        )],
    indices = [
        Index(value = ["userId"])
    ]
)
data class Library(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val userId: String,
    val history: String
)

data class LibraryById(
    val id:Int
)
