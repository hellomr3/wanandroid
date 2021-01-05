package com.looptry.wanandroid.ui.rv

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.looptry.wanandroid.BR
import com.looptry.wanandroid.R
import com.looptry.wanandroid.ext.getDrawableRes
import com.looptry.wanandroid.ext.launchAsyncRequest
import com.looptry.wanandroid.ui.rv.item.GridItem
import com.looptry.wanandroid.ui.rv.item.ListItem
import com.looptry.wanandroid.ui.rv.item.StringItem
import kotlinx.coroutines.delay
import me.tatarka.bindingcollectionadapter2.ItemBinding
import me.tatarka.bindingcollectionadapter2.OnItemBind

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
class RVViewModel : ViewModel() {

    val items = ObservableArrayList<Any>()

    val adapter = MultAdapter()
    val itemBinding = OnItemBind<Any> { itemBinding, position, item ->
        when (item) {
            is StringItem -> {
                itemBinding.set(BR.item, R.layout.item_string)
            }
            is GridItem -> {
                itemBinding.set(BR.item, R.layout.item_grid)
            }
            is ListItem -> {
                itemBinding.set(BR.item, R.layout.item_list)
            }
        }
    }
    val notify = MutableLiveData<Boolean>()

    fun getList() = launchAsyncRequest {
        delay(200)
        val list = mutableListOf<Any>(
        ).apply {
            add(ListItem(getDrawableRes(R.drawable.ic_camera), "审批信息列表"))
            //1
            add(StringItem("行政", topRightRadius = 32f, topLeftRadius = 32f))
            add(GridItem(getDrawableRes(R.drawable.ic_camera), "请假"))
            add(GridItem(getDrawableRes(R.drawable.ic_camera), "请假"))
            add(GridItem(getDrawableRes(R.drawable.ic_camera), "请假"))
            add(GridItem(getDrawableRes(R.drawable.ic_camera), "请假"))
            add(GridItem(getDrawableRes(R.drawable.ic_camera), "请假"))
            add(GridItem(getDrawableRes(R.drawable.ic_camera), "请假"))
            add(GridItem(getDrawableRes(R.drawable.ic_camera), "请假"))
            add(GridItem(getDrawableRes(R.drawable.ic_camera), "请假"))
            add(StringItem("", bottomRightRadius = 32f, bottomLeftRadius = 32f))
            //2
            add(StringItem("行政2", topRightRadius = 32f, topLeftRadius = 32f))
            add(GridItem(getDrawableRes(R.drawable.ic_camera), "请假"))
            add(GridItem(getDrawableRes(R.drawable.ic_camera), "请假"))
            add(GridItem(getDrawableRes(R.drawable.ic_camera), "请假"))
            add(GridItem(getDrawableRes(R.drawable.ic_camera), "请假"))
            add(GridItem(getDrawableRes(R.drawable.ic_camera), "请假"))
            add(GridItem(getDrawableRes(R.drawable.ic_camera), "请假"))
            add(GridItem(getDrawableRes(R.drawable.ic_camera), "请假"))
            add(GridItem(getDrawableRes(R.drawable.ic_camera), "请假"))
            add(StringItem("", bottomRightRadius = 16f, bottomLeftRadius = 16f))
        }
        items.addAll(list)
        notify.value = true
    }
}