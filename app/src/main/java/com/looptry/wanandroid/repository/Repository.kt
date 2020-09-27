package com.looptry.wanandroid.repository

import com.looptry.architecture.request.Result
import com.looptry.architecture.request.doOnException
import com.looptry.wanandroid.net.RequestApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
class Repository @Inject constructor(
    private val api: RequestApi
) : IRequest {

    /**
     * 用于执行后台请求
     */
    private suspend inline fun <reified T> invokeRequest(
        context: CoroutineContext = Dispatchers.Default,
        noinline block: suspend CoroutineScope.() -> Result<T>
    ): Result<T> {
        val result = try {
            withContext(context) {
                block.invoke(this)
            }
        } catch (e: Throwable) {
            Result.Exception(e)
        }
        result.doOnException {
            //doSomeThing
        }
        return result
    }

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