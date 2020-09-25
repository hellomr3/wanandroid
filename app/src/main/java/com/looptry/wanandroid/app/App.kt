package com.looptry.wanandroid.app

import android.app.Application
import com.blankj.utilcode.util.Utils
import dagger.hilt.android.HiltAndroidApp

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        //初始化Utils
        Utils.init(this)
    }
}