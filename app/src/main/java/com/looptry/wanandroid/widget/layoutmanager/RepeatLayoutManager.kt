package com.looptry.wanandroid.widget.layoutmanager

import androidx.recyclerview.widget.RecyclerView
import com.looptry.wanandroid.ext.logE

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
class RepeatLayoutManager : RecyclerView.LayoutManager() {
    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return RecyclerView.LayoutParams(
            RecyclerView.LayoutParams.WRAP_CONTENT,
            RecyclerView.LayoutParams.WRAP_CONTENT,
        )
    }

    override fun canScrollHorizontally(): Boolean {
        return true
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
        super.onLayoutChildren(recycler, state)
        if (recycler == null || state == null) return
        if (itemCount <= 0) return
        if (state.isPreLayout) return
        //将所有的Item分离至scrap
        detachAndScrapAttachedViews(recycler)
        var left = paddingLeft
        var position = 0
        while (left <= width - paddingRight) {
            val itemView = recycler.getViewForPosition(position)
            addView(itemView)
            measureChildWithMargins(itemView, 0, 0)
            //计算当前view可布局位置
            val right = left + getDecoratedMeasuredWidth(itemView)
            val top = paddingTop
            val bottom = top + getDecoratedMeasuredHeight(itemView) - paddingBottom
            //对子view进行布局
            layoutDecorated(itemView, left, top, right, bottom)
            left = right
            position++
        }
    }

    override fun scrollHorizontallyBy(
        dx: Int,
        recycler: RecyclerView.Recycler?,
        state: RecyclerView.State?
    ): Int {
        if (recycler == null || state == null) return dx
        fill(recycler, dx)
        offsetChildrenHorizontal(-dx)
        recyclerChildren(recycler, dx)
        return dx
    }

    private fun fill(recycler: RecyclerView.Recycler, dx: Int) {
        if (childCount == 0) return
        //左划 填充尾部
        if (dx > 0) {
            var anchorView = getChildAt(childCount - 1) ?: return
            val position = getPosition(anchorView)
            //可以填充页面时
            while (width - anchorView.right > 0) {
                val newPosition = (position + 1 + itemCount) % itemCount
                val newView = recycler.getViewForPosition(newPosition)
                addView(newView)
                measureChildWithMargins(newView, 0, 0)
                //layout
                val left = anchorView.right
                val top = paddingTop
                val right = left + getDecoratedMeasuredWidth(newView)
                val bottom = top + getDecoratedMeasuredHeight(newView) - paddingBottom
                layoutDecoratedWithMargins(newView, left, top, right, bottom)
                anchorView = newView
            }
        } else {
            //右划 填充头
            var headView = getChildAt(0) ?: return
            val position = getPosition(headView)
            //headView已经划完全
            var i = 0
            while (headView.left > paddingLeft) {
                val newPosition = (position - 1 + itemCount) % itemCount
                val newView = recycler.getViewForPosition(newPosition)
                addView(newView, 0)
                measureChildWithMargins(newView, 0, 0)
                //layout
                val right = headView.left
                val left = right - getDecoratedMeasuredWidth(newView)
                val top = paddingTop
                val bottom = top + getDecoratedMeasuredHeight(newView) - paddingBottom
                layoutDecoratedWithMargins(newView, left, top, right, bottom)
                //更新当前头view
                headView = newView
                i++
            }
        }
    }

    private fun recyclerChildren(recycler: RecyclerView.Recycler, dx: Int) {
        //左划 回收头部
        if (dx > 0) {
            val headView = getChildAt(0) ?: return
            val position = getPosition(headView)
            //可见->不可见
            if (headView.right < paddingLeft) {
                removeAndRecycleView(headView, recycler)
            }
        } else {
            //回收尾部
            val anchorView = getChildAt(childCount - 1) ?: return
            val position = getPosition(anchorView)
            if (anchorView.left <= paddingRight) {
                removeAndRecycleView(anchorView, recycler)
            }
        }
    }
}