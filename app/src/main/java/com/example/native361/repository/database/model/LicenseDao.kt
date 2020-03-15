/*
 * Copyright (c) 2020. No name
 */

package com.example.native361.repository.database.model

import androidx.room.*

@Dao
interface LicenseDao {
    @Query("SELECT * FROM git_hub_license")
    suspend fun getAll(): List<License>

    @Query("SELECT * FROM git_hub_license WHERE `key` = :key LIMIT 1")
    suspend fun findByKey(key: String): License?

    @Insert
    suspend fun insertAll(vararg license: License)

    @Update
    suspend fun update(vararg license: License)

    @Delete
    suspend fun delete(license: License)
}
