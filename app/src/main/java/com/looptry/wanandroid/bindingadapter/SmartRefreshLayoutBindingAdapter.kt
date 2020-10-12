package com.looptry.wanandroid.bindingadapter

import androidx.databinding.BindingAdapter
import com.looptry.wanandroid.ext.logE
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener
import com.scwang.smart.refresh.layout.listener.OnRefreshListener

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
@BindingAdapter(value = ["canLoadMore", "canRefresh", "finishAll"], requireAll = false)
fun SmartRefreshLayout.setState(
    canLoadMore: Boolean,
    canRefresh: Boolean,
    finish: Boolean
) {
    this.setEnableLoadMore(canLoadMore)
    this.setEnableRefresh(canRefresh)
    if (finish) {
        this.finishLoadMore()
        this.finishRefresh()
    }

}

@BindingAdapter(value = ["onRefreshListener", "onLoadMoreListener"], requireAll = false)
fun SmartRefreshLayout.setListener(l1: OnRefreshListener?, l2: OnLoadMoreListener?) {
    "onRefresh:$l1,onLoadMore:$l2".logE()
    this.setOnRefreshListener(l1)
    this.setOnLoadMoreListener(l2)
}