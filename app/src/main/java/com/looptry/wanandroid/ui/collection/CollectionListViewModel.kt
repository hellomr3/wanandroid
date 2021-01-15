package com.looptry.wanandroid.ui.collection

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.looptry.architecture.request.doOnSuccess
import com.looptry.wanandroid.R
import com.looptry.wanandroid.config.Config
import com.looptry.wanandroid.ext.launchAsyncRequest
import com.looptry.wanandroid.model.entity.article.ShareArticle
import com.looptry.wanandroid.model.mapper.ShareArticle2ShareArticleItem
import com.looptry.wanandroid.model.view.ShareArticleItem
import com.looptry.wanandroid.repository.IUser
import me.tatarka.bindingcollectionadapter2.BR
import me.tatarka.bindingcollectionadapter2.ItemBinding
import me.tatarka.bindingcollectionadapter2.collections.AsyncDiffObservableList

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
class CollectionListViewModel @ViewModelInject constructor(
    val repository: IUser
) : ViewModel() {

    var pageNo = Config.PAGE_PRIMARY

    //收藏列表
    private val origins = MutableLiveData<List<ShareArticle>>()

    val list = origins.map {
        it.map {
            ShareArticle2ShareArticleItem.map(it)
        }
    }

    val canLoadMore = MutableLiveData(false)
    val finishAll = MutableLiveData(true)

    val items = AsyncDiffObservableList<ShareArticleItem>(ShareArticleItem.diff)
    val itemBinding = ItemBinding.of<ShareArticleItem> { itemBinding, position, item ->
        itemBinding.set(BR.item, R.layout.item_collection)
    }

    fun getCollectionList(page: Int, showLoading: Boolean) =
        launchAsyncRequest(showLoading = showLoading) {
            val result = repository.getCollectList(pageNo)
            result.doOnSuccess {
                //是否可以加载更多
                canLoadMore.value = it.page < it.pageCount
                if (page == Config.PAGE_PRIMARY) {
                    origins.value = it.datas
                } else {
                    origins.value = (origins.value ?: emptyList()) + it.datas
                }
                //更新页码
                pageNo = page
            }
        }
}