package com.looptry.wanandroid.app

import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.ActivityUtils
import com.looptry.protobuf.entity.message
import com.looptry.wanandroid.datastore.userInfoDataStore
import com.looptry.wanandroid.route.RouteConfig
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Cookie

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
object LoginManager {
    lateinit var context: Context

    //注入context
    fun init(context: Context) {
        this@LoginManager.context = context
    }

    var userInfo: message.UserInfo
        get() = runBlocking {
            return@runBlocking context.userInfoDataStore.data.first()
        }
        set(value) = runBlocking {
            context.userInfoDataStore.updateData {
                it.toBuilder()
                    .setUserName(value.userName)
                    .setPassword(value.password)
                    .build()
            }
        }

    //保存登录的cookie
    val cookie = mutableListOf<Cookie>()

    val logged: Boolean
        get() {
            return cookie.isNotEmpty()
        }

    //前往登录页面
    fun toLogin() {
        ARouter.getInstance()
            .build(RouteConfig.PAGE_APP_LOGIN)
            .navigation()
    }

    //保存用户名和密码
    fun saveLocalInfo(username: String, password: String) {
        this.userInfo = this.userInfo.toBuilder()
            .setUserName(username)
            .setPassword(password)
            .build()
    }
}