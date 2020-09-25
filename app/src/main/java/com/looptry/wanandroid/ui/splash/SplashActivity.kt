package com.looptry.wanandroid.ui.splash

import android.content.Intent
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.looptry.architecture.page.DataBindingConfig
import com.looptry.wanandroid.BR
import com.looptry.wanandroid.R
import com.looptry.wanandroid.ui.main.MainActivity
import com.looptry.wanandroid.widget.activity.BaseActivity
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

    override fun onResume() {
        super.onResume()
        lifecycleScope.launchWhenResumed {
            delay(1000)
            toNextPage()
        }
    }

    private fun toNextPage() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}