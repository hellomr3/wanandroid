package com.looptry.wanandroid.model.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.recyclerview.widget.DiffUtil
import com.looptry.wanandroid.R
import com.looptry.wanandroid.ext.getDrawableRes

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
data class ShareArticleItem(
    val id: Int,
    val createTime: Long,
    val shareName: String,                  //分享人名称/作者
    val timeDesc: String,
    val title: String,
    val selection: String,                  //栏目
    var stared: Boolean,
    val link: String,                       //地址
    val top: Boolean,                       //是否置顶
    val fresh: Boolean                      //最新？
) {
    //处理用户点赞
    val star = MutableLiveData(stared)

    val starDrawable =
        star.map { if (it) getDrawableRes(R.drawable.home_ic_liked) else getDrawableRes(R.drawable.home_ic_like) }

    companion object {
        val diff = object : DiffUtil.ItemCallback<ShareArticleItem>() {
            override fun areItemsTheSame(
                oldItem: ShareArticleItem,
                newItem: ShareArticleItem
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: ShareArticleItem,
                newItem: ShareArticleItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}