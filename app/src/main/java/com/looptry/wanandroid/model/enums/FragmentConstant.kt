package com.looptry.wanandroid.model.enums

import androidx.annotation.IntDef

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
object FragmentConstant {
    const val REQUEST_EACH_TIME = 0x0001
    const val REQUEST_FIRST = 0x0002
    const val REQUEST_NONE = 0x0004

    @Target(AnnotationTarget.TYPE)
    @IntDef(REQUEST_EACH_TIME, REQUEST_FIRST, REQUEST_NONE)
    @Retention(AnnotationRetention.SOURCE)
    annotation class FragmentRequest
}