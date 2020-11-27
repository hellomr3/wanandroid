package com.looptry.wanandroid.utils

import android.graphics.*
import android.graphics.drawable.Drawable
import com.blankj.utilcode.util.ImageUtils

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
class WatermarkDrawable(
    private val src: Bitmap,
    private val nickname: String,
    private val avatar: Bitmap,
    private val textSize: Float = 24f,            //头像以及单个字体的边长
    private val space: Float = 8f                   //间隔
) : Drawable() {

    private val paint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG)
    }

    //待处理的文字
    private val undoText = "@$nickname"

    private var textMeasureBounds: Rect = Rect()

    init {
        setBounds(0, 0, src.width, src.height)
        //设置字体大小
        paint.textSize = textSize
        //获取字体所占
        paint.getTextBounds(undoText, 0, undoText.length, textMeasureBounds)
    }


    override fun draw(canvas: Canvas) {
        //画src
        canvas.drawBitmap(src, bounds.left.toFloat(), bounds.top.toFloat(), paint)
        val side = textMeasureBounds.bottom - textMeasureBounds.top
        val length = 3 * space + (1 + nickname.length + 1) * side
        //头像
        val newBitmap = ImageUtils.compressByScale(
            avatar,
            side / avatar.width.toFloat(),
            side / avatar.height.toFloat()
        )
        val avatarLeft = src.width - length
        val avatarTop = src.height - space - side + (side - textSize)
        canvas.drawBitmap(
            newBitmap,
            avatarLeft,
            avatarTop,
            paint
        )
        //字体颜色
        paint.color = Color.parseColor("#666666")
        //字
        canvas.drawText(
            "@${nickname}",
            src.width - length + space * 2 + side,
            src.height - space,
            paint
        )
    }

    override fun getIntrinsicHeight(): Int {
        return src.height
    }

    override fun getIntrinsicWidth(): Int {
        return src.width
    }

    override fun setAlpha(alpha: Int) {
        paint.alpha = alpha
        invalidateSelf()
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        paint.colorFilter = colorFilter
        invalidateSelf()
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT
    }

}