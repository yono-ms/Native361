/*
 * Copyright (c) 2020. No name
 */

package com.example.native361.repository.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "git_hub_license")
data class License(
    @PrimaryKey val key: String,
    val name: String,
    val node_id: String,
    val spdx_id: String,
    val url: String,
    val time_stamp: Date
)