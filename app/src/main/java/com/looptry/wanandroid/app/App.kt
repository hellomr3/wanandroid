package com.looptry.wanandroid.app

import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.Utils
import com.looptry.architecture.application.BasicApplication
import com.looptry.wanandroid.BuildConfig
import com.looptry.wanandroid.R
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.MaterialHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.api.RefreshFooter
import com.scwang.smart.refresh.layout.api.RefreshHeader
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.DefaultRefreshFooterCreator
import com.scwang.smart.refresh.layout.listener.DefaultRefreshHeaderCreator
import com.shuyu.gsyvideoplayer.player.IjkPlayerManager
import com.shuyu.gsyvideoplayer.player.PlayerFactory
import dagger.hilt.android.HiltAndroidApp
import io.microshow.rxffmpeg.RxFFmpegInvoke
import tv.danmaku.ijk.media.exo2.Exo2PlayerManager
import tv.danmaku.ijk.media.player.IjkMediaPlayer


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
        RxFFmpegInvoke.getInstance().setDebug(true)
        //ARouter
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
            ARouter.printStackTrace()
        }
        ARouter.init(this)
        //LoginManager
        LoginManager.init(this)

        //player
//        PlayerFactory.setPlayManager(Exo2PlayerManager::class.java)
        IjkPlayerManager.setLogLevel(IjkMediaPlayer.IJK_LOG_INFO)
    }

    init {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
            MaterialHeader(context)
        }
        SmartRefreshLayout.setDefaultRefreshFooterCreator { context, layout ->
            ClassicsFooter(context).apply {
                setFinishDuration(0)
                setDrawableSize(20f)
            }
        }
    }
}