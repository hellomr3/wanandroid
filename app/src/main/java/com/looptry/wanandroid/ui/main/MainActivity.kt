package com.looptry.wanandroid.ui.main

import android.view.MenuItem
import androidx.activity.viewModels
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.looptry.architecture.page.DataBindingConfig
import com.looptry.wanandroid.BR
import com.looptry.wanandroid.R
import com.looptry.wanandroid.adapter.MainPageAdapter
import com.looptry.wanandroid.databinding.ActivityMainBinding
import com.looptry.wanandroid.ext.logE
import com.looptry.wanandroid.model.enums.MainPage
import com.looptry.wanandroid.route.RouteConfig
import com.looptry.wanandroid.widget.activity.BaseActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
@Route(path = "/app/Main")
class MainActivity : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private val viewModel by viewModels<MainViewModel>()

    private val binding by lazy {
        mBinding as ActivityMainBinding
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(BR.vm, R.layout.activity_main, viewModel)
            .addBindingParams(BR.click, ClickProxy())
    }

    override fun initView() {
        super.initView()
        //viewPager
        with(binding.mainViewPager) {
            adapter = MainPageAdapter(this@MainActivity)
            isUserInputEnabled = false
            currentItem = MainPage.HOME
        }
        //bottomNavigationView
        binding.bottomNav.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val position = when (item.itemId) {
            R.id.bottom_nav -> MainPage.NAV
            R.id.bottom_mine -> MainPage.MINE
            else -> MainPage.HOME
        }
        //点击后与viewPager联动
        binding.mainViewPager.setCurrentItem(position, false)
        return true
    }

    inner class ClickProxy {

    }
}