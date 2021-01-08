package com.looptry.wanandroid.ui.water

import android.graphics.*
import android.graphics.drawable.Drawable
import androidx.core.graphics.scale
import com.blankj.utilcode.util.DeviceUtils
import com.looptry.wanandroid.R
import com.looptry.wanandroid.ext.getColorRes
import com.looptry.wanandroid.ext.logE
import com.looptry.wanandroid.ext.toPx
import com.luck.picture.lib.tools.BitmapUtils
import com.luck.picture.lib.tools.ScreenUtils

/**
 * Author: mr.3
 * Date:
 * Desc:当前用户的水印图片
 * Modify By:
 * Modify Date:
 */
class WaterDrawable(
    private var originAvatar: Bitmap,             //用户头像
    private val originNickName: String,           //用户昵称
    private val textSize: Float = 14.toPx().toFloat()
) : Drawable() {

    private lateinit var avatar: Bitmap

    private val nickname = "@$originNickName"

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = this@WaterDrawable.textSize
    }

    private val textBounds = Rect()

    private val padding = 2.toPx()

    private val margin = 2.toPx()

    private val isPortrait = originAvatar.height >= originAvatar.width

    init {
        //计算文字显示bounds
        paint.getTextBounds(nickname, 0, nickname.length, textBounds)
        val textHeight = paint.fontMetrics.descent - paint.fontMetrics.ascent
        //计算height
        val height = padding * 2 + paint.fontSpacing
        //计算width
        val width = margin + textHeight + margin + textBounds.right - textBounds.left
        setBounds(0, 0, width.toInt(), height.toInt())
        //avatar按比例放缩
        scaleAvatar(textHeight)
    }

    private fun scaleAvatar(r: Float) {
        //横向
        val scale = if (originAvatar.width >= originAvatar.height) {
            r / originAvatar.height
        } else {
            r / originAvatar.width
        }
        val newWidth = originAvatar.width * scale
        val newHeight = originAvatar.height * scale
        avatar = originAvatar.scale(newWidth.toInt(), newHeight.toInt(), true)
    }


    override fun draw(c: Canvas) {
        //画头像
        c.save()
        c.clipPath(Path().apply {
            addCircle(
                bounds.bottom / 2f,
                bounds.bottom / 2f,
                bounds.bottom / 2f - padding,
                Path.Direction.CW
            )
        })
        val offset = if (avatar.width >= avatar.height) {
            (avatar.width - avatar.height) / 2f
        } else {
            (avatar.height - avatar.width) / 2f
        }
        if (isPortrait) {
            c.drawBitmap(avatar, padding.toFloat(), padding.toFloat() - offset, paint)
        } else {
            c.drawBitmap(avatar, padding.toFloat() - offset, padding.toFloat(), paint)
        }
        c.restore()
        //
        val left = bounds.bottom + 2f
        val text = "@$originNickName"
        val textBound = Rect()
        paint.getTextBounds(text, 0, text.length, textBound)
        val textHeight = textBound.bottom - textBound.top
        val y = (bounds.bottom - textHeight) / 2f + textHeight - paint.fontMetrics.descent
        c.drawText(text, left, y, paint)
    }

    override fun setAlpha(p0: Int) {
        paint.alpha = alpha
        invalidateSelf()
    }

    override fun setColorFilter(p0: ColorFilter?) {
        paint.colorFilter = colorFilter
        invalidateSelf()
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT
    }


    override fun getIntrinsicHeight(): Int {
        return bounds.bottom
    }

    override fun getIntrinsicWidth(): Int {
        return bounds.right
    }
}