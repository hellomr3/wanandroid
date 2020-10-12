package com.looptry.wanandroid.model.mapper

import androidx.lifecycle.MutableLiveData
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
        val shareName = if (input.author.isNullOrBlank()) input.shareUser else input.author
        val timeDesc = input.niceShareDate
        val title = input.title
        val selection = "${input.superChapterName}.${input.chapterName}"
        val star = MutableLiveData(input.zan == 1)
        val link = input.link
        val item = ShareArticleItem(
            id = input.id,
            createTime = input.publishTime,
            shareName,
            timeDesc,
            title,
            selection,
            star,
            link
        )
        return item
    }
}