package com.looptry.wanandroid.net

import com.looptry.wanandroid.model.entity.ApiResult
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

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
}