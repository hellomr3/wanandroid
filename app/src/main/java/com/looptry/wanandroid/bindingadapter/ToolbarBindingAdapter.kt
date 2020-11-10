package com.looptry.wanandroid.bindingadapter

import androidx.databinding.BindingAdapter
import com.blankj.utilcode.util.ActivityUtils
import com.google.android.material.appbar.MaterialToolbar

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
@BindingAdapter("finish", requireAll = false)
fun MaterialToolbar.bindFinish(
    finish: Boolean
) {
    if (finish) {
        this.setNavigationOnClickListener {
            //结束当前页面
            ActivityUtils.getTopActivity().finish()
        }
    }
}