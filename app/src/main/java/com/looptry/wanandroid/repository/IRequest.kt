package com.looptry.wanandroid.repository

import com.looptry.architecture.request.Result

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
interface IRequest {
    suspend fun getBannerList(): Result<List<Any>>
}