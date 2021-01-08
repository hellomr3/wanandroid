package com.looptry.wanandroid.ui.water

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
class WaterViewModel : ViewModel() {

    val srcVideo = MutableLiveData<String>()

    val srcImage = MutableLiveData<String>()

    val tempFile = MutableLiveData<String>()

    val waterFile = MutableLiveData<String>()

    val transImageFile = MutableLiveData<String>()
}