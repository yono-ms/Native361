/*
 * Copyright (c) 2020. No name
 */

package com.example.native361.repository.database.model

import androidx.room.*

@Dao
interface SearchHistoryDao {
    @Query("SELECT * FROM git_hub_search_history")
    suspend fun getAll(): List<SearchHistory>

    @Query("SELECT login FROM git_hub_search_history")
    suspend fun getAllLogin(): List<String>

    @Query("SELECT * FROM git_hub_search_history WHERE login = :login LIMIT 1")
    suspend fun findByLogin(login: String): SearchHistory?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg searchHistory: SearchHistory)

    @Update
    suspend fun update(vararg searchHistory: SearchHistory)

    @Delete
    suspend fun delete(vararg searchHistory: SearchHistory)
}
