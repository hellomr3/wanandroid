package com.looptry.wanandroid.app

import com.blankj.utilcode.util.Utils
import com.looptry.architecture.application.BasicApplication
import dagger.hilt.android.HiltAndroidApp

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
@HiltAndroidApp
class App : BasicApplication() {
    override fun onCreate() {
        super.onCreate()
        //初始化Utils
        Utils.init(this)
    }
}