package com.looptry.wanandroid.ui.rv

import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.looptry.architecture.page.DataBindingConfig
import com.looptry.wanandroid.BR
import com.looptry.wanandroid.R
import com.looptry.wanandroid.databinding.ActivityRvBinding
import com.looptry.wanandroid.ui.rv.decoration.SpaceDecoration
import com.looptry.wanandroid.widget.activity.BaseActivity


/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
class RVActivity : BaseActivity() {

    private val viewModel by viewModels<RVViewModel>()

    private val binding by lazy {
        mBinding as ActivityRvBinding
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(BR.vm, R.layout.activity_rv, viewModel)
    }

    override fun initObserver() {
        super.initObserver()
        viewModel.notify.observe(this) {
            binding.rv.addItemDecoration(SpaceDecoration())
        }
    }

    override fun initView() {
        super.initView()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getList()
    }
}