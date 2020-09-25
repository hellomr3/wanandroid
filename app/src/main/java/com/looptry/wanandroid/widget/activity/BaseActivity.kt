package com.looptry.wanandroid.widget.activity

import android.content.Intent
import android.os.Bundle
import com.looptry.architecture.page.BasicActivity

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
abstract class BaseActivity : BasicActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObserver()
        initIntent()
        initView()
    }

    open fun initObserver() {}
    open fun initIntent() {}
    open fun initView() {}

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        initIntent()
    }
}