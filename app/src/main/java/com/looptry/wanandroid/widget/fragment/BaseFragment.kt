package com.looptry.wanandroid.widget.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import com.looptry.architecture.page.BasicActivity
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

    //判断是否已经进行请求
    var requestFlag = false

    //请求类型
    open val requestMode: @FragmentConstant.FragmentRequest Int = FragmentConstant.REQUEST_FIRST

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onResume() {
        super.onResume()
        //在onResume中进行观察者的初始
        initObserver()
        //根据请求类型处理请求信息
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

    // 1.初始化View
    open fun initView() {}

    // 2.初始化观察者
    open fun initObserver() {}

    // 3.请求网络
    open fun request() {

    }
}