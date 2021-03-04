package com.looptry.wanandroid.ui.splash

import android.content.Intent
import android.content.res.AssetManager
import android.os.Bundle
import androidx.activity.viewModels
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.facade.callback.NavigationCallback
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.constant.PermissionConstants
import com.blankj.utilcode.util.EncryptUtils
import com.looptry.architecture.ext.logD
import com.looptry.architecture.livedata.observeEvent
import com.looptry.architecture.page.DataBindingConfig
import com.looptry.wanandroid.BR
import com.looptry.wanandroid.R
import com.looptry.wanandroid.ext.logE
import com.looptry.wanandroid.ext.requestPermission
import com.looptry.wanandroid.ui.water.WaterActivity
import com.looptry.wanandroid.widget.activity.BaseActivity
import com.looptry.wanandroid.widget.service.TimedTaskService
import dalvik.system.DexClassLoader
import javax.crypto.Cipher

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
@Route(path = "/app/Splash")
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

    override fun initObserver() {
        super.initObserver()
        viewModel.nav.observeEvent(this) {
            toNextPage()
        }
    }

    override fun onResume() {
        super.onResume()
        requestPermission(PermissionConstants.STORAGE, onDenied = {}, onGrantedOrSuccess = {
            viewModel.tryLogin()
//            //先将assets中的文件拷贝到本地目录
//            val dexPath = ""
//            val loader = DexClassLoader(dexPath, cacheDir.path, null, null)
        })

    }


    private fun toNextPage() {
        ARouter.getInstance()
            .build("/app/AddressBook")
            .navigation()
        finish()
    }

}