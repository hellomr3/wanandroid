package com.looptry.wanandroid.model.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.recyclerview.widget.DiffUtil
import com.looptry.wanandroid.R
import com.looptry.wanandroid.ext.getDrawableRes
import com.looptry.wanandroid.model.entity.article.ShareArticle

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
data class ShareArticleItem(
    val entity: ShareArticle,
    val desc: String
) {

    companion object {
        val diff = object : DiffUtil.ItemCallback<ShareArticleItem>() {
            override fun areItemsTheSame(
                oldItem: ShareArticleItem,
                newItem: ShareArticleItem
            ): Boolean {
                return oldItem.entity.id == newItem.entity.id
            }

            override fun areContentsTheSame(
                oldItem: ShareArticleItem,
                newItem: ShareArticleItem
            ): Boolean {
                return oldItem.entity == newItem.entity
            }
        }
    }

    //分享人或作者
    val shareName = if (entity.author.isBlank()) entity.shareUser else entity.author

    //时间
    val timeDesc = entity.niceShareDate

    //标题
    val title = entity.title

    //是否置顶
    val top = entity.top

    //是否是新文章
    val fresh = entity.fresh

    //栏目
    val section = "${entity.superChapterName}.${entity.chapterName}"

    //用户收藏
    val collection = MutableLiveData(entity.collect)

    val collectionDrawable =
        collection.map { if (it) getDrawableRes(R.drawable.home_ic_liked) else getDrawableRes(R.drawable.home_ic_like) }

}