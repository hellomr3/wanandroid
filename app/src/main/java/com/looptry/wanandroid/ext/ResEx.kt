package com.looptry.wanandroid.ext

import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.Utils

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
/*获取颜色*/
fun getColorRes(color: Int): Int {
    return ContextCompat.getColor(Utils.getApp(), color)
}

/*获取文本*/
fun getStringRes(string: Int, vararg args: Any): String {
    return ActivityUtils.getTopActivity()?.getString(string, *args)
        ?: Utils.getApp().getString(string, *args)
}

/*获取drawable*/
fun getDrawableRes(drawable: Int): Drawable? {
    return ContextCompat.getDrawable(Utils.getApp(), drawable)
}