package com.looptry.wanandroid.net.intercept

import android.util.Log
import com.blankj.utilcode.util.LogUtils
import com.looptry.wanandroid.ext.logE
import okhttp3.logging.HttpLoggingInterceptor

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */

class LogIntercept {
    companion object {
        private const val TAG = "Http"
        fun getHttpLogInterceptor() =
            HttpLoggingInterceptor {
                it.logE(TAG)
            }.apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            }
    }
}