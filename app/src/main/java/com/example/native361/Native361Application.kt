/*
 * Copyright (c) 2020. No name
 */

package com.example.native361

import android.app.Application
import androidx.room.Room
import com.example.native361.repository.database.GitHubDatabase
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class Native361Application : Application() {
    companion object {
        val logger: Logger = LoggerFactory.getLogger(Native361Application::class.java.simpleName)
        lateinit var db: GitHubDatabase
    }

    override fun onCreate() {
        super.onCreate()
        logger.info("onCreate")
        db = Room.databaseBuilder(this, GitHubDatabase::class.java, "github_database").build()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        logger.info("onLowMemory")
    }

    override fun onTerminate() {
        super.onTerminate()
        logger.info("onTerminate")
    }
}