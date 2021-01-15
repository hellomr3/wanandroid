package com.looptry.wanandroid.repository

import com.looptry.architecture.request.Result
import com.looptry.wanandroid.model.entity.PageResp
import com.looptry.wanandroid.model.entity.article.ShareArticle

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
interface IUser {
    suspend fun login(username: String, password: String): Result<Any>

    suspend fun register(username: String, password: String, repassword: String): Result<Any>

    suspend fun collectInner(id: Int): Result<Any>

    suspend fun collectOut(title: String, author: String, link: String): Result<Any>

    suspend fun cancelArticle(id: Int): Result<Any>

    suspend fun cancelCollect(id: Int, originId: String): Result<Any>

    suspend fun getCollectList(pageNo: Int): Result<PageResp<ShareArticle>>
}