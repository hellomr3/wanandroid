package com.looptry.wanandroid.net

import com.looptry.architecture.request.Result
import com.looptry.wanandroid.model.entity.ApiResult
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
interface RequestApi {

    /**
     * 获取首页列表
     */
    @GET("/article/list/{page}/json")
    suspend fun getArticleList(@Path("page") page: Int): ApiResult<Any>

    /**
     * 获取Banner
     */
    @GET("/banner/json")
    suspend fun getBannerList(): ApiResult<List<Any>>
}