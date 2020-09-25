package com.looptry.wanandroid.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.looptry.architecture.request.Result
import com.looptry.architecture.request.doOnSuccess
import com.looptry.wanandroid.net.RequestApi
import com.looptry.wanandroid.repository.IRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
class HomeViewModel @ViewModelInject constructor(private val repository: IRequest) : ViewModel() {

    //获取Banner
    fun getBannerList() = viewModelScope.launch {
        withContext(Dispatchers.Default) {
            val resp = repository.getBannerList()
            resp.doOnSuccess {

            }
            when (resp) {
                is Result.OK -> {
                }
                is Result.Failure -> {
                }
                is Result.Exception -> {
                }
            }
        }
    }

    //获取首页栏目和数据
}