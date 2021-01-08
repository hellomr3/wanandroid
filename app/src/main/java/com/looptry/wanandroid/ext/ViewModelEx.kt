package com.looptry.wanandroid.ext

import android.content.Context
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.ActivityUtils
import com.looptry.architecture.ext.toEvent
import com.looptry.wanandroid.widget.activity.BaseActivity
import com.looptry.wanandroid.widget.viewmodel.ShareViewModel
import dagger.hilt.android.qualifiers.ActivityContext
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */

suspend fun CoroutineContext.launchWithExceptionHandler(
    handler: CoroutineExceptionHandler,
    block: suspend CoroutineScope.() -> Unit
) {
    supervisorScope {
        launch(this@launchWithExceptionHandler + handler) {
            block()
        }
    }
}

fun ViewModel.launchAsync(
    context: CoroutineContext = Dispatchers.Main,
    onStart: (() -> Unit)? = null,
    onFailure: ((Throwable) -> Unit)? = null,
    onFinished: (() -> Unit)? = null,
    block: suspend CoroutineScope.() -> Unit
) {
    //监督异常
    val handler = CoroutineExceptionHandler { _, throwable ->
        onFailure?.invoke(throwable)
    }
    this.viewModelScope.launch(context + handler) {
        try {
            onStart?.invoke()
            block.invoke(this)
        } catch (e: Throwable) {
            if (e is CancellationException) {
                //doNothing
                "CancellationException".logE()
            } else {
                onFailure?.invoke(e)
            }
        } finally {
            try {
                onFinished?.invoke()
            } catch (e: Throwable) {
                //doNothing
            }
        }
    }
}

/**
 * 处理请求中的异常
 */
fun ViewModel.launchAsyncRequest(
    context: CoroutineContext = Dispatchers.Main,
    showLoading: Boolean = false,
    onFinished: () -> Unit = {},
    block: suspend CoroutineScope.() -> Unit
) = viewModelScope.launch(context) {

    val shareViewModel = (ActivityUtils.getTopActivity() as BaseActivity).shareViewModel
    //监督异常
    val handler = CoroutineExceptionHandler { _, throwable ->
        handleException(throwable, onFinished)
    }
    supervisorScope {
        launch(handler) {
            try {
                if (showLoading) shareViewModel.showLoading.postValue(true.toEvent())
                this.coroutineContext.launchWithExceptionHandler(handler, block)
            } finally {
                if (showLoading) shareViewModel.showLoading.postValue(false.toEvent())
                onFinished.invoke()
            }
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

