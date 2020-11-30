package com.looptry.wanandroid.ext

import android.content.Context
import android.os.Handler
import com.blankj.utilcode.constant.PermissionConstants
import com.blankj.utilcode.util.PermissionUtils
import com.looptry.wanandroid.R


/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */

fun Context.requestPermission(
   vararg permissions: String,//需要获取的权限
    onDenied: () -> Unit,
    onGrantedOrSuccess: () -> Unit
) {
    PermissionConstants.STORAGE
    if (!PermissionUtils.isGranted(*permissions)) {
        PermissionUtils.permission(*permissions)
            .rationale { _, shouldRequest ->
                //拒绝后再次请求
                "无权限".showToast()
                //前往设置页面
                Handler().postDelayed(Runnable {
                    PermissionUtils.launchAppDetailsSettings()
                }, 1000)
            }
            .callback(object : PermissionUtils.SimpleCallback {
                override fun onGranted() {
                    onGrantedOrSuccess.invoke()
                }

                override fun onDenied() {
                    onDenied.invoke()
                }
            })
            .request()
    } else {
        onGrantedOrSuccess.invoke()
    }
}