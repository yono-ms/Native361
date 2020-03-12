/*
 * Copyright (c) 2020. No name
 */

package com.example.native361.ui.splash

import com.example.native361.ui.BaseViewModel
import com.example.native361.ui.DialogMessage

class SplashViewModel : BaseViewModel() {

    fun start() {
        logger.debug("start")
        appViewModel.dialogMessage.value = DialogMessage("Initialize", "SplashViewModel")
    }
}
