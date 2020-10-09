package com.looptry.wanandroid.widget.activity

import android.content.Intent
import android.os.Bundle
import com.looptry.architecture.livedata.observeEvent
import com.looptry.architecture.page.BasicActivity
import com.looptry.wanandroid.ext.logE
import com.looptry.wanandroid.widget.viewmodel.ShareViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
@AndroidEntryPoint
abstract class BaseActivity : BasicActivity() {

    @Inject
    lateinit var shareViewModel: ShareViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObserver()
        initIntent()
        initView()
    }

    open fun initObserver() {
        //加载框
        shareViewModel.showLoading.observeEvent(this) {
//            "showLoading:${this.javaClass.simpleName},$it".logE()
        }
    }

    open fun initIntent() {}
    open fun initView() {}

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        initIntent()
    }
}