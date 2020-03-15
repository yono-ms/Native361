/*
 * Copyright (c) 2020. No name
 */

package com.example.native361.repository.database.model

import androidx.room.*

@Dao
interface RepoDao {
    @Query("SELECT * FROM git_hub_repo")
    suspend fun getAll(): List<Repo>

    @Query("SELECT * FROM git_hub_repo WHERE id = :id LIMIT 1")
    suspend fun findById(id: Int): Repo?

    @Query("SELECT * FROM git_hub_repo WHERE owner = :userId")
    suspend fun getAllByUser(userId: Int): List<Repo>

    @Insert
    suspend fun insertAll(vararg repo: Repo)

    @Update
    suspend fun update(vararg repo: Repo)

    @Delete
    suspend fun delete(repo: Repo)
}
