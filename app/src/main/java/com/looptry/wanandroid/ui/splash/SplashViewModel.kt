package com.looptry.wanandroid.ui.splash

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.looptry.architecture.ext.toEvent
import com.looptry.architecture.livedata.EventMutableLiveData
import com.looptry.wanandroid.app.LoginManager
import com.looptry.wanandroid.ext.launchAsyncRequest
import com.looptry.wanandroid.repository.IUser

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
class SplashViewModel @ViewModelInject constructor(
    val repository: IUser
) : ViewModel() {

    val nav = EventMutableLiveData<Boolean>()

    fun tryLogin() = launchAsyncRequest {
        val userInfo = LoginManager.userInfo
        //登录
        repository.login(userInfo.userName, userInfo.password)
        nav.value = true.toEvent()
    }

}