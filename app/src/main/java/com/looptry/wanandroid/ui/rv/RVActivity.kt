package com.looptry.wanandroid.ui.rv

import androidx.activity.viewModels
import com.looptry.architecture.page.DataBindingConfig
import com.looptry.wanandroid.BR
import com.looptry.wanandroid.R
import com.looptry.wanandroid.ui.rv.decoration.SpaceDecoration
import com.looptry.wanandroid.widget.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_rv.*

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
class RVActivity : BaseActivity() {

    private val viewModel by viewModels<RVViewModel>()

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(BR.vm, R.layout.activity_rv, viewModel)
    }

    override fun initObserver() {
        super.initObserver()
        viewModel.notify.observe(this) {
            rv.addItemDecoration(SpaceDecoration())
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