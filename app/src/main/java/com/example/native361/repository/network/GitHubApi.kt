/*
 * Copyright (c) 2020. No name
 */

package com.example.native361.repository.network

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.coroutines.awaitStringResponseResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.slf4j.LoggerFactory

class GitHubApi {
    companion object {

        private val logger = LoggerFactory.getLogger(GitHubApi::class.java.simpleName)

        suspend fun get(url: String): String = withContext(Dispatchers.Default) {
            logger.info("get url=$url")
            val (request, response, result) = Fuel.get(url).awaitStringResponseResult()
            logger.info("$request")
            logger.info("$response")
            result.get()
        }
    }
}
