package com.looptry.wanandroid.ext

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.ActivityUtils
import com.looptry.architecture.ext.toEvent
import com.looptry.wanandroid.widget.activity.BaseActivity
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
/**
 * 处理请求中的异常
 */
fun ViewModel.launchAsyncRequest(
    context: CoroutineContext = Dispatchers.Main,
    showLoading: Boolean = false,
    onFinished: () -> Unit = {},
    block: suspend CoroutineScope.() -> Unit
) {
    val shareViewModel = (ActivityUtils.getTopActivity() as BaseActivity).shareViewModel
    //捕获异常
    val handler = CoroutineExceptionHandler { _, throwable ->
        handleException(throwable, onFinished)
    }
    viewModelScope.launch(handler + context) {
        try {
            try {
                if (showLoading) shareViewModel.showLoading.postValue(true.toEvent())
                supervisorScope {
                    block()
                }
            } finally {
                if (showLoading) shareViewModel.showLoading.postValue(false.toEvent())
                onFinished.invoke()
            }
        } catch (t: Throwable) {
            handleException(t, onFinished)
        }
    }
}

//处理异常
private fun handleException(e: Throwable, onFinished: () -> Unit) {
    e.printStackTrace()
    //onFinish
    onFinished.invoke()
    //处理错误
    when (e) {
        is CancellationException -> {

        }
        else -> {
            "服务请求出错".showToast()
        }
    }
}