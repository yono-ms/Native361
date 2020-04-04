/*
 * Copyright (c) 2020. No name
 */

package com.example.native361.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.example.native361.Native361Application
import com.example.native361.R
import com.example.native361.constant.RequestCode
import com.example.native361.repository.GitHubRepository
import com.example.native361.repository.database.model.Repo
import com.example.native361.repository.database.model.SearchHistory
import com.example.native361.ui.BaseViewModel
import com.example.native361.ui.DialogMessage
import kotlinx.coroutines.launch
import java.util.*

class HomeViewModel : BaseViewModel() {
    val login: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val isEnableSearch: LiveData<Boolean> = Transformations.map(login) {
        !it.isNullOrBlank()
    }
    val repos: MutableLiveData<List<Repo>> by lazy { MutableLiveData<List<Repo>>() }

    fun search() {
        logger.debug("search login=${login.value}")
        viewModelScope.launch {
            kotlin.runCatching {
                GitHubRepository.getRepos(login.value!!)
            }.onSuccess {
                repos.value = it
                if (it.count() == 0) {
                    appViewModel.dialogMessage.value = DialogMessage(
                        RequestCode.ALERT,
                        R.string.dialog_title_success,
                        R.string.dialog_message_no_repos
                    )
                } else {
                    Native361Application.db.searchHistoryDao()
                        .insertAll(SearchHistory(login.value!!, Date()))
                }
            }.onFailure {
                logger.error("Exception", it)
                appViewModel.dialogMessage.value = DialogMessage(
                    RequestCode.ALERT,
                    R.string.dialog_title_exception,
                    exception = it
                )
                repos.value = listOf()
            }
        }
    }

    fun history() {
        logger.debug("history login=${login.value}")
        viewModelScope.launch {
            kotlin.runCatching {
                Native361Application.db.searchHistoryDao().getAllLogin()
            }.onSuccess {
                if (it.isEmpty()) {
                    logger.debug("getAllLogin isEmpty")
                    appViewModel.dialogMessage.value = DialogMessage(
                        RequestCode.ALERT,
                        R.string.dialog_title_result,
                        R.string.dialog_message_no_history
                    )
                } else {
                    appViewModel.dialogMessage.value = DialogMessage(
                        RequestCode.SINGLE_CHOICE,
                        R.string.dialog_title_result,
                        items = it
                    )
                }
            }.onFailure {
                logger.error("Exception", it)
                appViewModel.dialogMessage.value = DialogMessage(
                    RequestCode.ALERT,
                    R.string.dialog_title_exception,
                    exception = it
                )
            }
        }
    }
}
