package com.looptry.db.converters

import androidx.room.TypeConverter
import java.util.*

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun date2Timestamp(date: Date?): Long? {
        return date?.time
    }
}