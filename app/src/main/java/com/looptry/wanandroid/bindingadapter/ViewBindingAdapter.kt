package com.looptry.wanandroid.bindingadapter

import android.view.View
import androidx.annotation.ColorInt
import androidx.databinding.BindingAdapter
import com.looptry.wanandroid.widget.DrawableCreator

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */

@BindingAdapter(
    value = [
        "strokeWidth",
        "strokeColor",
        "cornersRadius",
        "cornersBottomLeftRadius",
        "cornersBottomRightRadius",
        "cornersTopLeftRadius",
        "cornersTopRightRadius"
    ], requireAll = false
)
fun View.setBackgroundDrawable(
    strokeWidth: Float,
    strokeColor: Int,
    cornersRadius: Float,
    cornersBottomLeftRadius: Float,
    cornersBottomRightRadius: Float,
    cornersTopLeftRadius: Float,
    cornersTopRightRadius: Float,
) {
    //Stroke
    val builder = DrawableCreator.Builder()
    builder.setStrokeWidth(strokeWidth)
        .setStrokeColor(strokeColor)
    //CornerRadius
    if (
        cornersBottomLeftRadius != 0f ||
        cornersBottomRightRadius != 0f ||
        cornersTopLeftRadius != 0f ||
        cornersTopRightRadius != 0f
    ) {
        builder.setCornersRadius(
            cornersBottomLeftRadius,
            cornersBottomRightRadius,
            cornersTopLeftRadius,
            cornersTopRightRadius
        )
    } else {
        builder.setCornersRadius(cornersRadius)
    }

    this.background = builder.build()
}