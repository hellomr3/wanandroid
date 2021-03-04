package com.looptry.wanandroid.ui.qa

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.looptry.wanandroid.ext.launchAsync
import com.looptry.wanandroid.ext.launchAsyncRequest
import kotlinx.coroutines.delay

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
class QAViewModel : ViewModel() {
    val data = MutableLiveData<String>("")

    fun request() = launchAsync {
        delay(10)
        data.value = "data"
    }
}