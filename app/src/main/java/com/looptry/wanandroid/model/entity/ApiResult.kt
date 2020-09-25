package com.looptry.wanandroid.model.entity

import com.google.gson.annotations.SerializedName

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
data class ApiResult<T>(
    @SerializedName("data") val data: T,
    @SerializedName("errorCode") val code: Int,
    @SerializedName("errorMsg") val msg: String
)