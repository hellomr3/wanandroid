package com.looptry.wanandroid.ui.register

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
class RegisterViewModel @ViewModelInject constructor(

) : ViewModel() {

    val inputUserName = MutableLiveData<String>()

    val inputPassword = MutableLiveData<String>()

    val inputRePassword = MutableLiveData<String>()
}