/*
 * Copyright (c) 2020. No name
 */

package com.example.native361.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AppViewModel : ViewModel() {
    val dialogMessage: MutableLiveData<DialogMessage> = MutableLiveData()
}
