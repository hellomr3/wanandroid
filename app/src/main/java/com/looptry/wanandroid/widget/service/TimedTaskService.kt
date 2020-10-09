package com.looptry.wanandroid.widget.service

import android.content.Context
import android.content.Intent
import androidx.core.app.JobIntentService
import com.blankj.utilcode.util.Utils
import com.looptry.architecture.livedata.toEvent
import com.looptry.wanandroid.ext.logE
import com.looptry.wanandroid.widget.viewmodel.ShareViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.*

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
class TimedTaskService : JobIntentService() {


    companion object {
        private const val JOB_ID = 10000

        /**
         * 用于执行后台方法
         */
        fun enqueueWork(context: Context, intent: Intent) {
            enqueueWork(context, TimedTaskService::class.java, JOB_ID, intent)
        }
    }

    //获取注入入口
    @EntryPoint
    @InstallIn(ApplicationComponent::class)
    interface ServiceEntryPoint {
        fun requireShareViewModel(): ShareViewModel
    }

    override fun onCreate() {
        super.onCreate()
        "onCreate".logE()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onHandleWork(intent: Intent) = runBlocking {
        timedTask()
    }

    //在后台执行定时任务
    private suspend fun timedTask() = withContext(Dispatchers.Default) {
        val point = EntryPointAccessors.fromApplication(
            Utils.getApp(),
            ServiceEntryPoint::class.java
        )
        val shareViewModel = point.requireShareViewModel()
        while (true) {
//            "hei,i'm always online".logE()
            delay(1000)
            val loading = !shareViewModel.showLoading.value!!.peekContent()
            shareViewModel.showLoading.postValue(loading.toEvent())
        }
    }
}