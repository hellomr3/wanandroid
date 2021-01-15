package com.looptry.wanandroid.net

import com.looptry.wanandroid.model.entity.ApiResult
import com.looptry.wanandroid.model.entity.PageResp
import com.looptry.wanandroid.model.entity.article.ShareArticle
import retrofit2.http.*

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
interface UserApi {

    /**
     * 用户登录
     */
    @POST("/user/login")
    @FormUrlEncoded
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): ApiResult<Any>

    /**
     * 用户注册
     */
    @FormUrlEncoded
    @POST("/user/register")
    suspend fun register(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("repassword") repassword: String
    ): ApiResult<Any>

    /**
     * 退出登录
     */
    @GET("/user/logout/json")
    suspend fun logout(): ApiResult<Any>

    /**
     * 获取收藏文章列表
     */
    @GET("/lg/collect/list/{page}/json")
    suspend fun getCollectList(@Path("page") page: Int): ApiResult<PageResp<ShareArticle>>

    /**
     * 收藏站内文章
     */
    @POST("/lg/collect/{id}/json")
    suspend fun collectionInner(@Path("id") id: Int): ApiResult<Any>

    /**
     * 收藏站外文章
     */
    @POST("/lg/collect/add/json")
    @FormUrlEncoded
    suspend fun collectOut(
        @Field("title") title: String,
        @Field("author") author: String,
        @Field("link") link: String
    ): ApiResult<Any>

    /**
     * 取消收藏（文章列表）
     */
    @POST("/lg/uncollect_originId/{id}/json")
    suspend fun uncollectFromArticleList(@Path("id") id: Int): ApiResult<Any>

    /**
     * 取消收藏（收藏页面）
     */
    @POST("/lg/uncollect/{id}/json")
    @FormUrlEncoded
    suspend fun uncollectFromCollectList(
        @Path("id") id: Int,
        @Field("originId") originId: String
    ): ApiResult<Any>
}