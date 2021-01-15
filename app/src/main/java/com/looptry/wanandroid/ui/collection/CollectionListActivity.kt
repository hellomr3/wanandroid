package com.looptry.wanandroid.ui.collection

import androidx.activity.viewModels
import com.alibaba.android.arouter.facade.annotation.Route
import com.looptry.architecture.BR
import com.looptry.architecture.page.DataBindingConfig
import com.looptry.wanandroid.R
import com.looptry.wanandroid.databinding.ActivityLoginBinding
import com.looptry.wanandroid.route.RouteConfig
import com.looptry.wanandroid.widget.activity.BaseActivity
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener
import com.scwang.smart.refresh.layout.listener.OnRefreshListener
import dagger.hilt.android.AndroidEntryPoint

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
@AndroidEntryPoint
@Route(path = RouteConfig.PAGE_MINE_COLLECT_LIST, extras = RouteConfig.EXTRA_NEED_LOGIN)
class CollectionListActivity : BaseActivity() {

    private val viewModel by viewModels<CollectionListViewModel>()

    private val binding by lazy {
        mBinding as ActivityLoginBinding
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(BR.vm, R.layout.activity_collection_list, viewModel)
            .addBindingParams(BR.click, ClickProxy())
    }

    override fun initObserver() {
        super.initObserver()
        //收藏列表
        viewModel.list.observe(this) {
            viewModel.items.update(it)
        }
    }

    override fun requestOnCreate() {
        super.requestOnCreate()
        request(0, true)
    }

    private fun request(pageNo: Int, showLoading: Boolean) {
        viewModel.getCollectionList(pageNo, showLoading)
    }

    inner class ClickProxy : OnRefreshListener, OnLoadMoreListener {
        override fun onRefresh(refreshLayout: RefreshLayout) {
            request(0, false)
        }

        override fun onLoadMore(refreshLayout: RefreshLayout) {
            request(viewModel.pageNo + 1, false)
        }
    }
}