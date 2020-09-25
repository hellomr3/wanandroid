package com.looptry.wanandroid.adapter

import android.util.SparseArray
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.getOrDefault
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.looptry.wanandroid.model.enums.MainPage
import com.looptry.wanandroid.ui.home.HomeFragment
import com.looptry.wanandroid.ui.mine.MineFragment
import com.looptry.wanandroid.ui.nav.NavFragment

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
class MainPageAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    private val map = SparseArray<Fragment>().apply {
        put(MainPage.HOME, HomeFragment.newInstance())
        put(MainPage.NAV, NavFragment.newInstance())
        put(MainPage.MINE, MineFragment.newInstance())
    }

    override fun getItemCount(): Int {
        return map.size()
    }

    override fun createFragment(position: Int): Fragment {
        return map.getOrDefault(position, HomeFragment.newInstance())
    }
}