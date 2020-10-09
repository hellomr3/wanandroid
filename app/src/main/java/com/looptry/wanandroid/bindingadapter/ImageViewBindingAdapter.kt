package com.looptry.wanandroid.bindingadapter

import android.graphics.drawable.ColorDrawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.looptry.wanandroid.R
import com.looptry.wanandroid.ext.getColorRes

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
@BindingAdapter(value = ["bindUrl"], requireAll = false)
fun ImageView.glideUrl(url: String) {
    Glide.with(this.context)
        .load(url)
        .centerCrop()
        .placeholder(ColorDrawable(getColorRes(R.color.colorMain)))
        .into(this)
}