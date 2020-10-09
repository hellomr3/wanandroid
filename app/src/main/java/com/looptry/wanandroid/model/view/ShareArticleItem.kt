package com.looptry.wanandroid.model.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.looptry.wanandroid.R
import com.looptry.wanandroid.ext.getDrawableRes

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
data class ShareArticleItem(
    val id: Int,
    val shareName: String,
    val timeDesc: String,
    val title: String,
    val selection: String,
    var star: MutableLiveData<Boolean>,
    val link: String
) {
    val starDrawable =
        star.map { if (it) getDrawableRes(R.drawable.home_ic_liked) else getDrawableRes(R.drawable.home_ic_like) }

}