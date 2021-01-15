package com.looptry.wanandroid.ui.mine

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.alibaba.android.arouter.launcher.ARouter
import com.looptry.architecture.BR
import com.looptry.architecture.page.DataBindingConfig
import com.looptry.wanandroid.R
import com.looptry.wanandroid.route.RouteConfig
import com.looptry.wanandroid.widget.fragment.BaseFragment

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
class MineFragment : BaseFragment() {
    companion object {
        fun newInstance(): MineFragment {
            val args = Bundle()
            val fragment = MineFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private val viewModel by viewModels<MineViewModel>()

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(BR.vm, R.layout.fragment_mine, viewModel)
            .addBindingParams(BR.click, ClickProxy())
    }

    inner class ClickProxy {
        fun toCollectList() {
            ARouter.getInstance()
                .build(RouteConfig.PAGE_MINE_COLLECT_LIST)
                .navigation()
        }
    }
}