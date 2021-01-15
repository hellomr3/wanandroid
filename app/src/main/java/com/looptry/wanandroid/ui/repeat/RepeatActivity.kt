package com.looptry.wanandroid.ui.repeat

import android.os.Bundle
import androidx.activity.viewModels
import com.looptry.architecture.BR
import com.looptry.architecture.page.DataBindingConfig
import com.looptry.wanandroid.R
import com.looptry.wanandroid.databinding.ActivityRepeatBinding
import com.looptry.wanandroid.widget.activity.BaseActivity
import com.looptry.wanandroid.widget.layoutmanager.RepeatLayoutManager

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
class RepeatActivity : BaseActivity() {

    private val viewModel by viewModels<RepeatViewModel>()

    private val binding by lazy {
        mBinding as ActivityRepeatBinding
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(BR.vm, R.layout.activity_repeat, viewModel)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.request()
    }

    override fun initObserver() {
        super.initObserver()
        viewModel.list.observe(this) {
            viewModel.items.update(it)
        }
    }

    override fun initView() {
        super.initView()
        binding.rv.layoutManager = RepeatLayoutManager()
    }
}