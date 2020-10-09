package com.looptry.wanandroid.bindingadapter

import androidx.databinding.BindingAdapter
import com.scwang.smart.refresh.layout.SmartRefreshLayout

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
@BindingAdapter(value = ["canLoadMore", "canRefresh", "finishAll"], requireAll = false)
fun SmartRefreshLayout.finishAll(canLoadMore: Boolean, canRefresh: Boolean, finish: Boolean) {
    this.setEnableLoadMore(canLoadMore)
    this.setEnableRefresh(canRefresh)
    if (finish) {
        this.finishLoadMore()
        this.finishRefresh()
    }

}