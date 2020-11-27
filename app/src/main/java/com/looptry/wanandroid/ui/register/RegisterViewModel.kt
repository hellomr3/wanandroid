package com.looptry.wanandroid.ui.register

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.looptry.architecture.request.doOnFailure
import com.looptry.architecture.request.doOnSuccess
import com.looptry.wanandroid.R
import com.looptry.wanandroid.ext.getStringRes
import com.looptry.wanandroid.ext.launchAsyncRequest
import com.looptry.wanandroid.ext.showToast
import com.looptry.wanandroid.repository.IRequest

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
class RegisterViewModel @ViewModelInject constructor(
    val repository: IRequest
) : ViewModel() {

    val inputUserName = MutableLiveData<String>()

    val inputPassword = MutableLiveData<String>()

    val inputRePassword = MutableLiveData<String>()

    fun register(username: String, password: String, repassword: String) = launchAsyncRequest {
        val result = repository.register(username, password, repassword)
        result.doOnSuccess {
            getStringRes(R.string.register_success).showToast()
        }
        result.doOnFailure {
            it.showToast()
        }
    }
}