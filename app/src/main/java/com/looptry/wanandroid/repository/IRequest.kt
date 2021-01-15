package com.looptry.wanandroid.repository

import com.looptry.architecture.request.Result
import com.looptry.wanandroid.model.entity.PageResp
import com.looptry.wanandroid.model.entity.article.ShareArticle
import com.looptry.wanandroid.model.entity.banner.BannerInfo

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
interface IRequest {
    suspend fun getBannerList(): Result<List<BannerInfo>>

    suspend fun getTopArticles(): Result<List<ShareArticle>>

    suspend fun getArticleList(page: Int): Result<PageResp<ShareArticle>>

}