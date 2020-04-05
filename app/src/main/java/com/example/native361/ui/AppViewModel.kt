/*
 * Copyright (c) 2020. No name
 */

package com.example.native361.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.native361.constant.Destination
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AppViewModel : ViewModel() {
    /**
     * targetを渡すためFragmentが処理する.
     * backgroundのfragmentはnullを受け取るので再上面fragmentだけが処理できる.
     */
    val dialogMessage: MutableLiveData<DialogMessage> = MutableLiveData()

    /**
     * activityが処理する.
     * 遷移した瞬間にforegroundが非nullを受け取ることになるためfragmentで処理できない.
     */
    val destination: MutableLiveData<Destination> = MutableLiveData()

    val isBusy: MutableLiveData<Boolean> = MutableLiveData(false)

    fun checkBusy(): Boolean {
        return if (isBusy.value == true) {
            true
        } else {
            isBusy.value = true
            false
        }
    }

    fun releaseBusy() {
        viewModelScope.launch {
            delay(200)
            isBusy.postValue(false)
        }
    }
}
