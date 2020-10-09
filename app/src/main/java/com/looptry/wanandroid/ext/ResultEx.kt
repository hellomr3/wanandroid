package com.looptry.wanandroid.ext

import com.looptry.architecture.request.Result
import com.looptry.wanandroid.R
import com.looptry.wanandroid.model.entity.ApiResult

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */

/**
 * 处理请求结果
 */
fun <T> ApiResult<T>.handleResult(): Result<T> {
    val code = this.code
    return when {
        code == 0 -> {
            Result.OK(this.data)
        }
        code == -1001 -> {
            Result.Failure(getStringRes(R.string.request_loginExpired))
        }
        code < 0 -> {
            Result.Failure(this.msg)
        }
        else -> Result.Failure(getStringRes(R.string.request_unknownCode, code))
    }
}