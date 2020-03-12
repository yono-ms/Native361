/*
 * Copyright (c) 2020. No name
 */

package com.example.native361.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.native361.ui.AppViewModel
import com.example.native361.ui.BaseViewModel
import com.example.native361.ui.DialogMessage

class SplashViewModel(appViewModel: AppViewModel) : BaseViewModel(appViewModel) {
    class Factory(private val appViewModel: AppViewModel) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return SplashViewModel(appViewModel) as T
        }
    }

    fun start() {
        logger.debug("start")
        appViewModel.dialogMessage.value = DialogMessage("Initialize", "SplashViewModel")
    }
}
