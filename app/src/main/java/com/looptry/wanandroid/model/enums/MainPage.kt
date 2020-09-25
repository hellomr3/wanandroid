package com.looptry.wanandroid.model.enums

import androidx.annotation.IntDef


/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
object MainPage {
    const val HOME = 0x0
    const val NAV = 0x1
    const val MINE = 0x2

    @IntDef(
        value = [
            HOME, NAV, MINE
        ]
    )
    @Retention(AnnotationRetention.SOURCE)
    annotation class MainPage
}
