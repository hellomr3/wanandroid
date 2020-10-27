package com.looptry.wanandroid.ext

import com.blankj.utilcode.util.ConvertUtils

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */

fun Int.toPx() = ConvertUtils.dp2px(this.toFloat())

fun Float.toPx() = ConvertUtils.dp2px(this)