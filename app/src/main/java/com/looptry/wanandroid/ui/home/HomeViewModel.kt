package com.looptry.wanandroid.ui.home

import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.looptry.architecture.request.doOnSuccess
import com.looptry.wanandroid.BR
import com.looptry.wanandroid.R
import com.looptry.wanandroid.model.entity.article.ShareArticle
import com.looptry.wanandroid.model.entity.banner.BannerInfo
import com.looptry.wanandroid.model.mapper.ShareArticle2ShareArticleItem
import com.looptry.wanandroid.model.view.ShareArticleItem
import com.looptry.wanandroid.repository.IRequest
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.tatarka.bindingcollectionadapter2.OnItemBind
import me.tatarka.bindingcollectionadapter2.collections.DiffObservableList

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
class HomeViewModel @ViewModelInject constructor(
    private val repository: IRequest
) : ViewModel() {

    val nextPage = MutableLiveData(0)

    private val _banners = MutableLiveData<List<BannerInfo>>()

    val banners: LiveData<List<BannerInfo>> = _banners

    private val _shareArticles = MutableLiveData<List<ShareArticle>>(emptyList())

    val shareArticle = _shareArticles.map {
        it.map { item ->
            ShareArticle2ShareArticleItem.map(item)
        }
    }

    //列表
    val canLoadMore = MutableLiveData(false)
    val finishAll = MutableLiveData(true)

    val items = DiffObservableList<ShareArticleItem>(ShareArticleItem.diff)

    val itemBinding = OnItemBind<ShareArticleItem> { itemBinding, position, item ->
        itemBinding.set(BR.item, R.layout.item_share_article)
            .bindExtra(BR.click, View.OnClickListener {
                toCollection(item)
            })

    }

    //获取Banner
    fun getBannerList() = viewModelScope.launch {
        val resp = repository.getBannerList()
        resp.doOnSuccess {
            _banners.value = it
        }
    }

    //获取首页栏目和数据
    fun getArticleList(page: Int) = viewModelScope.launch {

        val result = repository.getArticleList(page)
        result.doOnSuccess { data ->
            //能否继续加载
            canLoadMore.value = data.page < data.pageCount
            //停止刷新、加载
            finishAll.value = true
            val oldItems = _shareArticles.value ?: emptyList()
            val newItems = data.datas
            //更新数据
            _shareArticles.value = if (page == 0) newItems else oldItems + newItems
            //更新nextPage
            nextPage.value = page + 1
        }
    }

    //收藏或取消收藏
    private fun toCollection(item: ShareArticleItem) = viewModelScope.launch {
        delay(1000)
        item.star.value = !item.star.value!!
    }
}