package com.looptry.wanandroid.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.blankj.utilcode.constant.PermissionConstants
import com.blankj.utilcode.util.PermissionUtils
import com.looptry.architecture.page.DataBindingConfig
import com.looptry.wanandroid.BR
import com.looptry.wanandroid.R
import com.looptry.wanandroid.ext.requestPermission
import com.looptry.wanandroid.ui.main.MainActivity
import com.looptry.wanandroid.widget.activity.BaseActivity
import com.looptry.wanandroid.widget.service.TimedTaskService
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
class SplashActivity : BaseActivity() {

    private val viewModel by viewModels<SplashViewModel>()

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(BR.vm, R.layout.activity_splash, viewModel)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //运行后台任务
        TimedTaskService.enqueueWork(this, Intent())
    }

    override fun onResume() {
        super.onResume()


        requestPermission(PermissionConstants.STORAGE,onDenied = {},onGrantedOrSuccess = {
            Thread.sleep(100)
            toNextPage()
        })
    }

    private fun toNextPage() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}