/*
 * Copyright (c) 2020. No name
 */

package com.example.native361.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.native361.repository.database.model.*

@Database(
    entities = [
        User::class,
        Repo::class,
        License::class,
        SearchHistory::class
    ],
    version = 1
)
@TypeConverters(GitHubConverter::class)
abstract class GitHubDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun repoDao(): RepoDao
    abstract fun licenseDao(): LicenseDao
    abstract fun searchHistoryDao(): SearchHistoryDao
}
