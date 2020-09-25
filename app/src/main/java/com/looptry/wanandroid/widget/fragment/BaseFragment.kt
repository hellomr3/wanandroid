package com.looptry.wanandroid.widget.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import com.looptry.architecture.page.BasicFragment
import com.looptry.wanandroid.model.enums.FragmentConstant

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
abstract class BaseFragment : BasicFragment() {

    //请求类型
    open val requestMode: @FragmentConstant.FragmentRequest Int = FragmentConstant.REQUEST_FIRST

    //判断是否已经进行请求
    var requestFlag = false

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObserver()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onResume() {
        super.onResume()
        when (requestMode) {
            FragmentConstant.REQUEST_NONE -> {
            }
            FragmentConstant.REQUEST_EACH_TIME -> {
                request()
            }
            FragmentConstant.REQUEST_FIRST -> {
                if (!requestFlag) {
                    requestFlag = true
                    request()
                }
            }
        }
    }

    // 1.初始化观察者
    open fun initObserver() {}

    // 2.初始化View
    open fun initView() {}

    // 3.请求网络
    open fun request() {

    }
}