package com.looptry.wanandroid.ui.home

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.looptry.architecture.page.DataBindingConfig
import com.looptry.wanandroid.BR
import com.looptry.wanandroid.R
import com.looptry.wanandroid.databinding.FragmentHomeBinding
import com.looptry.wanandroid.widget.fragment.BaseFragment
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

    private val binding by lazy {
        mBinding as FragmentHomeBinding
    }

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
            bannerAdapter.notifyDataSetChanged()
        }

        viewModel.shareArticle.observe(this) {
            val diffResult = viewModel.items.calculateDiff(it)
            viewModel.items.update(it, diffResult)
        }
    }

    override fun initView() {
        super.initView()
        //banner
        binding.banner.addBannerLifecycleObserver(this)
            .setAdapter(bannerAdapter)
//            .setBannerGalleryEffect(20,20,0,0.8f)
            .setBannerGalleryMZ(10, 1f)

        binding.homeRv.addItemDecoration(
            DividerItemDecoration(
                this.context,
                DividerItemDecoration.VERTICAL
            )
        )
        //关闭RecyclerView动画效果
        binding.homeRv.itemAnimator?.changeDuration = 0
        binding.homeRv.itemAnimator?.removeDuration = 0

//        homeRv.layoutManager = GridLayoutManager(this.context,4)

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
            val page = viewModel.pageNo + 1
            viewModel.getArticleList(page = page)
        }
    }
}