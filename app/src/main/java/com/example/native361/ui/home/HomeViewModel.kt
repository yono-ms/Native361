/*
 * Copyright (c) 2020. No name
 */

package com.example.native361.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.example.native361.repository.GitHubRepository
import com.example.native361.repository.database.model.Repo
import com.example.native361.ui.BaseViewModel
import com.example.native361.ui.DialogMessage
import kotlinx.coroutines.launch

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
                    appViewModel.dialogMessage.value = DialogMessage("No repos.", "Success")
                }
            }.onFailure {
                logger.error("Exception", it)
                appViewModel.dialogMessage.value = DialogMessage("${it.message}", "Exception", it)
                repos.value = listOf()
            }
        }
    }
}
