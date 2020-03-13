/*
 * Copyright (c) 2020. No name
 */

package com.example.native361.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.native361.constant.Destination

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
}
