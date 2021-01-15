package com.looptry.wanandroid.app

import android.app.Activity
import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter

/**
 * Author: mr.3
 * Date:
 * Desc: ARouter Scheme
 * Modify By:
 * Modify Date:
 */
class SchemeFilterActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val uri = intent.data
        ARouter.getInstance()
            .build(uri)
            .navigation()
        finish()
    }
}