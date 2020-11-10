package com.looptry.wanandroid.widget.viewmodel

import androidx.lifecycle.ViewModel
import com.looptry.architecture.ext.toEvent
import com.looptry.architecture.livedata.EventMutableLiveData


/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
class ShareViewModel : ViewModel() {

    val showLoading = EventMutableLiveData(false.toEvent())
}