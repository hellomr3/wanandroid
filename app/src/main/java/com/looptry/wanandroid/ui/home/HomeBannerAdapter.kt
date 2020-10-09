package com.looptry.wanandroid.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.looptry.wanandroid.BR
import com.looptry.wanandroid.R
import com.looptry.wanandroid.databinding.LayoutItemBannerBinding
import com.looptry.wanandroid.ext.showToast
import com.looptry.wanandroid.model.entity.banner.BannerInfo
import com.youth.banner.adapter.BannerAdapter

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
class HomeBannerAdapter : BannerAdapter<BannerInfo, HomeBannerAdapter.ViewHolder>(emptyList()) {

    inner class ViewHolder(val bind: ViewDataBinding) : RecyclerView.ViewHolder(bind.root) {

    }

    override fun onCreateHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val binding = DataBindingUtil.inflate<LayoutItemBannerBinding>(
            layoutInflater,
            R.layout.layout_item_banner,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindView(holder: ViewHolder?, data: BannerInfo?, position: Int, size: Int) {
        (holder as ViewHolder).let {
            it.bind.setVariable(BR.item, data)
            it.bind.setVariable(BR.click, View.OnClickListener {
                "hello".showToast()
            })
        }

    }
}