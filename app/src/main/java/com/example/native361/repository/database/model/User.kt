/*
 * Copyright (c) 2020. No name
 */

package com.example.native361.repository.database.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "git_hub_user", indices = [Index(value = ["login"])])
data class User(
    val avatar_url: String,
    val bio: String?,
    val blog: String,
    val company: String?,
    val created_at: Date,
    val email: String?,
    val events_url: String,
    val followers: Int,
    val followers_url: String,
    val following: Int,
    val following_url: String,
    val gists_url: String,
    val gravatar_id: String,
    val hireable: String?,
    val html_url: String,
    @PrimaryKey val id: Int,
    val location: String?,
    val login: String,
    val name: String?,
    val node_id: String,
    val organizations_url: String,
    val public_gists: Int,
    val public_repos: Int,
    val received_events_url: String,
    val repos_url: String,
    val site_admin: Boolean,
    val starred_url: String,
    val subscriptions_url: String,
    val type: String,
    val updated_at: Date,
    val url: String,
    val time_stamp: Date
)
