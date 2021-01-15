package com.looptry.wanandroid.route.intercept

import android.content.Context
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Interceptor
import com.alibaba.android.arouter.facade.callback.InterceptorCallback
import com.alibaba.android.arouter.facade.template.IInterceptor
import com.looptry.wanandroid.app.LoginManager
import com.looptry.wanandroid.route.RouteConfig

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
@Interceptor(priority = 99, name = "登录拦截器")
class LoginIntercept : IInterceptor {

    lateinit var context: Context

    override fun init(context: Context) {
        this.context = context
    }

    override fun process(postcard: Postcard, callback: InterceptorCallback) {
        when (postcard.extra) {
            RouteConfig.EXTRA_NEED_LOGIN -> {
                if (LoginManager.logged) {
                    callback.onContinue(postcard)
                } else {
                    handleNotLogin()
                    callback.onInterrupt(Throwable("用户未登录"))
                }
            }
            else -> {
                callback.onContinue(postcard)
            }
        }

    }

    private fun handleNotLogin() {
        LoginManager.toLogin()
    }

}