package com.looptry.wanandroid.repository

import com.looptry.architecture.request.Result
import com.looptry.wanandroid.net.RequestApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.take
import javax.inject.Inject

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
class Repository @Inject constructor(
    val api: RequestApi
) : IRequest {
    override suspend fun getBannerList(): Result<List<Any>> {
        //不用每次都获取Banner列表
        //定义一个缓存时间
        //未过缓存时间从db中获取
        return try {
            val resp = api.getBannerList()
            if (resp.code == 200) {
                Result.OK(resp.data)
            } else {
                Result.Failure(resp.msg)
            }
        } catch (e: Throwable) {
            e.printStackTrace()
            Result.Exception(e)
        }
    }

}