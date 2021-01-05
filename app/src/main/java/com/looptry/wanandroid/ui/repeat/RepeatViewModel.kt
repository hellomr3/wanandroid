package com.looptry.wanandroid.ui.repeat

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DiffUtil
import com.looptry.architecture.BR
import com.looptry.wanandroid.R
import com.looptry.wanandroid.ext.getDrawableRes
import com.looptry.wanandroid.ext.launchAsyncRequest
import kotlinx.coroutines.delay
import me.tatarka.bindingcollectionadapter2.OnItemBind
import me.tatarka.bindingcollectionadapter2.collections.AsyncDiffObservableList

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
class RepeatViewModel : ViewModel() {

    val list = MutableLiveData<List<String>>()

    val items = AsyncDiffObservableList<String>(object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    })

    val randomColor = listOf(
        getDrawableRes(R.color.color333),
        getDrawableRes(R.color.color666),
        getDrawableRes(R.color.color999),
        getDrawableRes(R.color.colorMain)
    )

    val itemBinding = OnItemBind<String> { itemBinding, position, item ->
        itemBinding.set(BR.item, R.layout.item_repeat)
            .bindExtra(BR.color, randomColor[position])
    }


    fun request() = launchAsyncRequest {
        delay(200)
        list.postValue(
            listOf("1", "2", "3", "4")
        )
    }
}