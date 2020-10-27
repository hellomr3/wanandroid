package com.looptry.wanandroid.bindingadapter

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.looptry.wanandroid.R
import com.looptry.wanandroid.app.GlideApp
import com.looptry.wanandroid.ext.getColorRes
import com.looptry.wanandroid.ext.getDrawableRes
import com.looptry.wanandroid.ext.toPx

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
@BindingAdapter(
    value = [
        "glideSrc",
        "glidePlaceholder",
        "glideRoundRadius",
        "glideTopLeftRadius",
        "glideTopRightRadius",
        "glideBottomLeftRadius",
        "glideBottomRightRadius"
    ],
    requireAll = false
)
fun ImageView.setGlide(
    src: Any?,
    placeholder: Drawable?,
    roundRadius: Int?,
    topLeftRadius: Int?,
    topRightRadius: Int?,
    bottomLeftRadius: Int?,
    bottomRightRadius: Int?
) {
    if (src == null) return
    val mPlaceholder = placeholder ?: getDrawableRes(R.color.colorMain)!!
    val transCorners: BitmapTransformation? = when {
        roundRadius != null && roundRadius > 0 -> {
            RoundedCorners(roundRadius.toPx())
        }
        (topLeftRadius != null) or
                (topRightRadius != null) or
                (bottomLeftRadius != null) or
                (bottomRightRadius != null) -> {
            GranularRoundedCorners(
                (topLeftRadius ?: 0).toPx().toFloat(),
                (topRightRadius ?: 0).toPx().toFloat(),
                (bottomLeftRadius ?: 0).toPx().toFloat(),
                (bottomRightRadius ?: 0).toPx().toFloat()
            )
        }
        else -> {
            null
        }
    }
    val trans = mutableListOf<BitmapTransformation>()
    //先判断scaleType
    when (scaleType) {
        ImageView.ScaleType.CENTER_CROP -> {
            trans.add(CenterCrop())
        }
    }
    //圆角
    transCorners?.let {
        trans.add(it)
    }
    val options = RequestOptions().transform(*trans.toTypedArray())
        .placeholder(mPlaceholder)
        .override(300, 300)
    //加载图片
    GlideApp.with(this.context)
        .load(src)
        .apply(options)
        .into(this)
}