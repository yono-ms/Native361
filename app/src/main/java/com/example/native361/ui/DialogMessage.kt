/*
 * Copyright (c) 2020. No name
 */

package com.example.native361.ui

data class DialogMessage(
    val message: String,
    val title: String,
    val exception: Throwable? = null
)
