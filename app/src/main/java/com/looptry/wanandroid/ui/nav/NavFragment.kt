package com.looptry.wanandroid.ui.nav

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.looptry.architecture.BR
import com.looptry.architecture.page.DataBindingConfig
import com.looptry.wanandroid.R
import com.looptry.wanandroid.widget.fragment.BaseFragment

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
class NavFragment : BaseFragment() {

    companion object {
        fun newInstance(): NavFragment {
            val args = Bundle()
            val fragment = NavFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private val viewModel by viewModels<NavViewModel>()

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(BR.vm, R.layout.fragment_nav, viewModel)
    }
}