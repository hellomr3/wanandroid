package com.looptry.wanandroid.net

import com.looptry.wanandroid.model.entity.ApiResult
import com.looptry.wanandroid.model.entity.PageResp
import com.looptry.wanandroid.model.entity.article.ShareArticle
import com.looptry.wanandroid.model.entity.banner.BannerInfo
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
     * 获取置顶文章
     */
    @GET("/article/top/json")
    suspend fun getTopArticleList(): ApiResult<List<ShareArticle>>

    /**
     * 获取分享文章列表
     */
    @GET("/article/list/{page}/json")
    suspend fun getArticleList(@Path("page") page: Int): ApiResult<PageResp<ShareArticle>>

    /**
     * 获取Banner
     */
    @GET("/banner/json")
    suspend fun getBannerList(): ApiResult<List<BannerInfo>>
}