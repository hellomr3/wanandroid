package com.looptry.wanandroid.ext

import com.blankj.utilcode.util.ToastUtils

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */

fun Any.showToast() {
    ToastUtils.showShort(this.toString())
}