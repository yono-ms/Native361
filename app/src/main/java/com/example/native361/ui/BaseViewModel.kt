/*
 * Copyright (c) 2020. No name
 */

package com.example.native361.ui

import androidx.lifecycle.ViewModel
import org.slf4j.Logger
import org.slf4j.LoggerFactory

open class BaseViewModel : ViewModel() {

    lateinit var appViewModel: AppViewModel

    val logger: Logger by lazy {
        LoggerFactory.getLogger(javaClass.simpleName)
    }
}