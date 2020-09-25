package com.looptry.wanandroid.ui.home

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.looptry.architecture.page.DataBindingConfig
import com.looptry.wanandroid.BR
import com.looptry.wanandroid.R
import com.looptry.wanandroid.widget.fragment.BaseFragment
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

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(BR.vm, R.layout.fragment_home, viewModel)
    }

    override fun request() {
        super.request()
        viewModel.getBannerList()
    }

}