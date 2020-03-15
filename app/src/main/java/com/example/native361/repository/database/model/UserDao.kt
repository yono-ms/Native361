/*
 * Copyright (c) 2020. No name
 */

package com.example.native361.repository.database.model

import androidx.room.*

@Dao
interface UserDao {
    @Query("SELECT * FROM git_hub_user")
    suspend fun getAll(): List<User>

    @Query("SELECT * FROM git_hub_user WHERE id = :id LIMIT 1")
    suspend fun findById(id: Int): User?

    @Query("SELECT * FROM git_hub_user WHERE login = :login LIMIT 1")
    suspend fun findByLogin(login: String): User?

    @Insert
    suspend fun insertAll(vararg user: User)

    @Update
    suspend fun update(vararg user: User)

    @Delete
    suspend fun delete(user: User)
}
