/*
 * Copyright (c) 2020. No name
 */

package com.example.native361.ui.splash

import androidx.lifecycle.viewModelScope
import com.example.native361.Native361Application
import com.example.native361.R
import com.example.native361.constant.Destination
import com.example.native361.constant.PreferenceKey
import com.example.native361.constant.RequestCode
import com.example.native361.ui.BaseViewModel
import com.example.native361.ui.DialogMessage
import kotlinx.coroutines.launch

class SplashViewModel : BaseViewModel() {

    fun start() {
        logger.debug("start")
        viewModelScope.launch {
            // 長い処理の後で入力を要求
            kotlin.runCatching {
                Native361Application.prefs.getBoolean(PreferenceKey.PROMPT.rawValue, false)
            }.onSuccess {
                if (it) {
                    appViewModel.dialogMessage.value =
                        DialogMessage(
                            RequestCode.ALERT,
                            R.string.app_name,
                            R.string.dialog_message_no_initialize
                        )
                } else {
                    appViewModel.destination.value = Destination.REPLACE_HOME
                }
            }.onFailure {
                appViewModel.dialogMessage.value =
                    DialogMessage(
                        RequestCode.ALERT,
                        R.string.dialog_title_exception,
                        exception = it
                    )
            }
        }
    }
}
