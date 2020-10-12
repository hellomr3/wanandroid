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
    val shareName: String,
    val timeDesc: String,
    val title: String,
    val selection: String,
    var star: MutableLiveData<Boolean>,
    val link: String
) {
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