package com.looptry.wanandroid.model.entity

import com.google.gson.annotations.SerializedName

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
data class PageResp<T>(
    @SerializedName("curPage") val page: Int,               //当前页码
    val datas: List<T>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,                                            //总页数
    val size: Int,
    val total: Int
)