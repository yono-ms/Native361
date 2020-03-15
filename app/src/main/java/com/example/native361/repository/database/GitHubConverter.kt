/*
 * Copyright (c) 2020. No name
 */

package com.example.native361.repository.database

import androidx.room.TypeConverter
import java.util.*

class GitHubConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}