package com.looptry.wanandroid.ui.main

import android.net.Uri
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.blankj.utilcode.util.FileUtils
import com.blankj.utilcode.util.ImageUtils
import com.blankj.utilcode.util.PathUtils
import com.blankj.utilcode.util.UriUtils
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.looptry.architecture.page.DataBindingConfig
import com.looptry.wanandroid.BR
import com.looptry.wanandroid.R
import com.looptry.wanandroid.adapter.MainPageAdapter
import com.looptry.wanandroid.ext.getDrawableRes
import com.looptry.wanandroid.ext.logE
import com.looptry.wanandroid.model.enums.MainPage
import com.looptry.wanandroid.utils.WaterUtils
import com.looptry.wanandroid.utils.WatermarkDrawable
import com.looptry.wanandroid.widget.activity.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import io.microshow.rxffmpeg.RxFFmpegCommandList
import io.microshow.rxffmpeg.RxFFmpegInvoke
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

@AndroidEntryPoint
class MainActivity : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private val viewModel by viewModels<MainViewModel>()

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(BR.vm, R.layout.activity_main, viewModel)
            .addBindingParams(BR.click, ClickProxy())
    }

    override fun initView() {
        super.initView()
        //viewPager
        with(mainViewPager) {
            adapter = MainPageAdapter(this@MainActivity)
            isUserInputEnabled = false
            currentItem = MainPage.HOME
        }
        //bottomNavigationView
        bottomNav.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val position = when (item.itemId) {
            R.id.bottom_nav -> MainPage.NAV
            R.id.bottom_mine -> MainPage.MINE
            else -> MainPage.HOME
        }
        //点击后与viewPager联动
        mainViewPager.setCurrentItem(position, false)
        return true
    }

    inner class ClickProxy {
        //视频水印
        fun water() {

            val videoPath =
                UriUtils.uri2File(Uri.parse("content://com.scst.hunanshinyp.utilcode.provider/external_path/DCIM/Camera/VID_20201104_17390330.mp4")).absolutePath
            val waterPath =
                UriUtils.uri2File(Uri.parse("content://com.looptry.wanandroid.utilcode.provider/external_files_path/Pictures/IMG_CMP_218233433.jpeg")).absolutePath
            val outPath =
                "${PathUtils.getDownloadCachePath()}${File.separator}${System.currentTimeMillis()}.mp4"
            lifecycleScope.launchWhenCreated {
                "$videoPath,${waterPath},${outPath}".logE()
                WaterUtils.water(videoPath, waterPath, outPath)
            }
        }
    }
}