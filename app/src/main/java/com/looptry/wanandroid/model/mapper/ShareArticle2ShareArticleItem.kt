package com.looptry.wanandroid.model.mapper

import com.looptry.architecture.model.mapper.Mapper
import com.looptry.wanandroid.model.entity.article.ShareArticle
import com.looptry.wanandroid.model.view.ShareArticleItem

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
object ShareArticle2ShareArticleItem : Mapper<ShareArticle, ShareArticleItem?> {
    override fun map(input: ShareArticle): ShareArticleItem {

        return ShareArticleItem(input, input::class.java.simpleName)
    }
}