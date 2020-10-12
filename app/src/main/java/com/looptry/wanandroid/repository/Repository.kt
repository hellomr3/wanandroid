package com.looptry.wanandroid.repository

import com.looptry.architecture.request.Result
import com.looptry.architecture.request.doOnException
import com.looptry.architecture.request.doOnFailure
import com.looptry.architecture.request.doOnSuccess
import com.looptry.wanandroid.ext.handleResult
import com.looptry.wanandroid.ext.logE
import com.looptry.wanandroid.model.entity.PageResp
import com.looptry.wanandroid.model.entity.article.ShareArticle
import com.looptry.wanandroid.model.entity.banner.BannerInfo
import com.looptry.wanandroid.net.RequestApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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
    val TAG = this::class.java.simpleName

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
            "onException:${it?.message}".logE(TAG)
            it?.printStackTrace()
        }
        result.doOnSuccess {
            "onSuccess:$it".logE(TAG)
        }
        result.doOnFailure {
            "onFailure:$it".logE(TAG)
        }
        return result
    }

    override suspend fun getBannerList(): Result<List<BannerInfo>> {
        //不用每次都获取Banner列表
        //定义一个缓存时间
        //未过缓存时间从db中获取
        return invokeRequest {
            api.getBannerList()
                .handleResult()
        }
    }

    override suspend fun getArticleList(page: Int): Result<PageResp<ShareArticle>> {
        return invokeRequest {
            val resp = api.getArticleList(page)
                .handleResult()
            resp
        }
    }

}