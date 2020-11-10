package com.looptry.wanandroid.ui.login

import android.content.Context
import android.content.Intent
import android.text.style.ClickableSpan
import android.view.View
import androidx.core.text.toSpannable
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.looptry.architecture.request.doOnFailure
import com.looptry.architecture.request.doOnSuccess
import com.looptry.wanandroid.R
import com.looptry.wanandroid.ext.*
import com.looptry.wanandroid.net.UserApi
import com.looptry.wanandroid.repository.IRequest
import com.looptry.wanandroid.repository.Repository
import com.looptry.wanandroid.ui.register.RegisterActivity
import dagger.hilt.android.qualifiers.ActivityContext

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
class LoginViewModel @ViewModelInject constructor(
    @ActivityContext private val context: Context,
    private val repository: IRequest,
) : ViewModel() {

    val inputUserName = MutableLiveData<String>()

    val inputPassword = MutableLiveData<String>()

    val toRegisterSpan = getStringRes(R.string.login_toRegister).toSpan().apply {
        setClickable(object : ClickableSpan() {
            override fun onClick(widget: View) {
                //前往注册页面
                RegisterActivity.navigation(context)
            }
        }, this.length - 2, this.length)
        setTextColor(getColorRes(R.color.colorMain), this.length - 2, this.length)
    }

    //用户登录
    fun login(username: String, password: String) = launchAsyncRequest(showLoading = true) {
        val result = repository.login(username, password)
        result.doOnSuccess {
            getStringRes(R.string.login_success).showToast()
        }
        result.doOnFailure {
            it.showToast()
        }
    }
}