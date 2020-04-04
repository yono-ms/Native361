/*
 * Copyright (c) 2020. No name
 */

package com.example.native361.repository.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "git_hub_search_history")
data class SearchHistory(
    @PrimaryKey val login: String,
    val time_stamp: Date
)
