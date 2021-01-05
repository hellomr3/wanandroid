package com.looptry.wanandroid.bindingadapter

import androidx.databinding.BindingAdapter
import com.looptry.wanandroid.R
import com.looptry.wanandroid.player.CoverPlayer

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
@BindingAdapter("cover", "url", requireAll = false)
fun CoverPlayer.bind(
    cover: String?,
    url: String?,
) {
    //设置封面
    if (!cover.isNullOrBlank()) {
        loadCoverImage(cover, R.color.colorMain)
    }
    //设置播放地址
    if (!url.isNullOrBlank()) {
        setUp(url, false, null)
    }
}