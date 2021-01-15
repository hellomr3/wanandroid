package com.looptry.wanandroid.ui.rv

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.looptry.wanandroid.ui.rv.item.GridItem
import com.looptry.wanandroid.ui.rv.item.ListItem
import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
class MultAdapter : BindingRecyclerViewAdapter<Any>() {

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        val layoutManager = recyclerView.layoutManager
        val adapter = recyclerView.adapter as BindingRecyclerViewAdapter<Any>
        if (layoutManager is GridLayoutManager) {
            layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    val item = adapter.getAdapterItem(position)
                    return when (item) {
                        is String -> {
                            4
                        }
                        is GridItem -> {
                            1
                        }
                        is ListItem -> {
                            4
                        }
                        else -> 4
                    }
                }
            }
        }
    }

    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
        super.onViewAttachedToWindow(holder)
    }
}