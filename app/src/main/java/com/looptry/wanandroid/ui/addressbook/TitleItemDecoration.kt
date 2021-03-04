package com.looptry.wanandroid.ui.addressbook

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.looptry.wanandroid.ext.toPx
import kotlin.math.roundToInt

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
class TitleItemDecoration : RecyclerView.ItemDecoration() {

    var ii: II? = null

    val headerHeight = 20.toPx()

    val bounds = Rect()

    val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 12.toPx().toFloat()
    }


    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        c.save()
        val left: Int
        val right: Int
        //noinspection AndroidLintNewApi - NewApi lint fails to handle overrides.
        if (parent.clipToPadding) {
            left = parent.paddingLeft
            right = parent.width - parent.paddingRight
            c.clipRect(
                left, parent.paddingTop, right,
                parent.height - parent.paddingBottom
            )
        } else {
            left = 0
            right = parent.width
        }

        val count = parent.childCount
        for (i in 0 until count) {
            val child = parent.getChildAt(i)
            parent.layoutManager?.getDecoratedBoundsWithMargins(child, bounds)
            val top = bounds.top
            val bottom = top + headerHeight

            val position = parent.getChildAdapterPosition(child)
            if (ii?.checkHead(position) == true) {
                parent.layoutManager?.getDecoratedBoundsWithMargins(child, bounds)
                val title = ii?.provideGroupName(position) ?: "#"
                val textBound = Rect()
                paint.getTextBounds(title, 0, title.length, textBound)
                val textHeight = textBound.height()
                val y = top + (bottom - top - textHeight) / 2f + textHeight
                c.drawText(ii?.provideGroupName(position) ?: "", 12.toPx().toFloat(), y, paint)
            }
        }
        c.restore()
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        if (ii?.checkHead(position) == true) {
            outRect.top = outRect.top + headerHeight
        }
    }

    //提供是否分组并且

    interface II {
        fun checkHead(position: Int): Boolean

        fun provideGroupName(position: Int): String
    }
}