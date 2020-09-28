package com.looptry.wanandroid.ui.main

import android.view.MenuItem
import androidx.activity.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.looptry.architecture.page.DataBindingConfig
import com.looptry.wanandroid.BR
import com.looptry.wanandroid.R
import com.looptry.wanandroid.adapter.MainPageAdapter
import com.looptry.wanandroid.model.enums.MainPage
import com.looptry.wanandroid.widget.activity.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

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

    }
}