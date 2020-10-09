package com.looptry.wanandroid.ui.home

import android.view.View
import androidx.databinding.ObservableArrayList
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

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
class HomeViewModel @ViewModelInject constructor(private val repository: IRequest) : ViewModel() {

    val page = MutableLiveData(0)

    private val _banners = MutableLiveData<List<BannerInfo>>()

    val banners: LiveData<List<BannerInfo>> = _banners

    private val _shareArticles = MutableLiveData<List<ShareArticle>>()

    val shareArticle = _shareArticles.map {
        it.map { item ->
            ShareArticle2ShareArticleItem.map(item)
        }
    }

    //列表
    val canLoadMore = MutableLiveData(false)
    val finishAll = MutableLiveData(true)

    val items = ObservableArrayList<ShareArticleItem>()

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
        val resp = repository.getArticleList(page)
        resp.doOnSuccess {
            _shareArticles.value = it
            //可以加载第二页
            this@HomeViewModel.page.value = this@HomeViewModel.page.value!! + 1
        }
    }

    //收藏或取消收藏
    private fun toCollection(item: ShareArticleItem) = viewModelScope.launch {
        delay(1000)
        item.star.value = !item.star.value!!
    }
}