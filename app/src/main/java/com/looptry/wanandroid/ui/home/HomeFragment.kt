package com.looptry.wanandroid.ui.home

import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.looptry.architecture.page.DataBindingConfig
import com.looptry.wanandroid.BR
import com.looptry.wanandroid.R
import com.looptry.wanandroid.ext.logE
import com.looptry.wanandroid.widget.fragment.BaseFragment
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener
import com.scwang.smart.refresh.layout.listener.OnRefreshListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.delay

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    companion object {
        fun newInstance(): HomeFragment {
            val args = Bundle()
            val fragment = HomeFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private val viewModel by viewModels<HomeViewModel>()

    private val bannerAdapter by lazy {
        HomeBannerAdapter()
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(BR.vm, R.layout.fragment_home, viewModel)
            .addBindingParams(BR.click, ClickProxy())
    }

    override fun initObserver() {
        super.initObserver()
        viewModel.banners.observe(this) {
            if (it.isEmpty()) return@observe
            bannerAdapter.setDatas(it)
            banner.start()
        }

        viewModel.shareArticle.observe(this) {
            viewModel.items.update(it)
        }
    }

    override fun initView() {
        super.initView()
        //banner
        banner.addBannerLifecycleObserver(this)
            .setAdapter(bannerAdapter)
//            .setBannerGalleryEffect(20,20,0,0.8f)
            .setBannerGalleryMZ(10, 1f)
        //关闭RecyclerView动画效果
        homeRv.itemAnimator?.changeDuration = 0
        homeRv.itemAnimator?.removeDuration = 0
    }

    override fun request() {
        super.request()
        viewModel.getBannerList()
        viewModel.getTopArticleList()
        viewModel.getArticleList(showLoading = true, 0)
    }

    inner class ClickProxy : OnRefreshListener, OnLoadMoreListener {
        override fun onRefresh(refreshLayout: RefreshLayout) {
            viewModel.getBannerList()
            viewModel.getTopArticleList()
            viewModel.getArticleList(page = 0)
        }

        override fun onLoadMore(refreshLayout: RefreshLayout) {
            val page = viewModel.nextPage.value!!
            viewModel.getArticleList(page = page)
        }
    }
}