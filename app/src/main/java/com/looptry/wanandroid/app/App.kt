package com.looptry.wanandroid.app

import android.content.Context
import com.blankj.utilcode.util.Utils
import com.looptry.architecture.application.BasicApplication
import com.looptry.wanandroid.R
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.MaterialHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.api.RefreshFooter
import com.scwang.smart.refresh.layout.api.RefreshHeader
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.DefaultRefreshFooterCreator
import com.scwang.smart.refresh.layout.listener.DefaultRefreshHeaderCreator
import dagger.hilt.android.HiltAndroidApp

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
@HiltAndroidApp
class App : BasicApplication() {
    override fun onCreate() {
        super.onCreate()
        //初始化Utils
        Utils.init(this)
    }

    init {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
            MaterialHeader(context)
        }
        SmartRefreshLayout.setDefaultRefreshFooterCreator { context, layout ->
            ClassicsFooter(context).apply {
                setDrawableSize(20f)
            }
        }
    }
}