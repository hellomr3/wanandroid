package com.looptry.wanandroid.ui.home

import android.content.Context
import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.looptry.architecture.request.doOnFailure
import com.looptry.architecture.request.doOnSuccess
import com.looptry.wanandroid.BR
import com.looptry.wanandroid.R
import com.looptry.wanandroid.config.Config
import com.looptry.wanandroid.ext.getStringRes
import com.looptry.wanandroid.ext.launchAsyncRequest
import com.looptry.wanandroid.ext.showToast
import com.looptry.wanandroid.model.entity.article.ShareArticle
import com.looptry.wanandroid.model.entity.banner.BannerInfo
import com.looptry.wanandroid.model.mapper.ShareArticle2ShareArticleItem
import com.looptry.wanandroid.model.view.ShareArticleItem
import com.looptry.wanandroid.repository.IRequest
import com.looptry.wanandroid.repository.IUser
import dagger.hilt.android.qualifiers.ActivityContext
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
    private val repository: IRequest,
    private val userRepository: IUser,
) : ViewModel() {

    var pageNo = Config.PAGE_PRIMARY

    private val _banners = MutableLiveData<List<BannerInfo>>()

    val banners: LiveData<List<BannerInfo>> = _banners

    private val _topArticles = MutableLiveData<List<ShareArticle>>(emptyList())
    private val _shareArticles = MutableLiveData<List<ShareArticle>>(emptyList())

    val shareArticle = MediatorLiveData<List<ShareArticleItem>>().apply {
        fun merge(
            topList: List<ShareArticle>,
            shareList: List<ShareArticle>
        ): List<ShareArticleItem> {
            return (topList + shareList).map {
                ShareArticle2ShareArticleItem.map(it)
            }
        }

        addSource(_topArticles) { topArticles ->
            val shareList = _shareArticles.value
            value = merge(topArticles, shareList ?: emptyList())
        }

        addSource(_shareArticles) { shareArticles ->
            val topList = _topArticles.value
            value = merge(topList ?: emptyList(), shareArticles)
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
    fun getBannerList() = launchAsyncRequest {
        val resp = repository.getBannerList()
        resp.doOnSuccess {
            _banners.value = it
        }
    }

    //获取首页置顶文章
    fun getTopArticleList() = launchAsyncRequest {
        val result = repository.getTopArticles()
        result.doOnSuccess {
            _topArticles.value = it
        }
        result.doOnFailure {
            it.showToast()
        }
    }

    //获取首页栏目和数据
    fun getArticleList(showLoading: Boolean = false, page: Int) =
        launchAsyncRequest(showLoading = showLoading, onFinished = {
            //结束加载、刷新
            finishAll.value = true
        }) {
            val result = repository.getArticleList(page)
            result.doOnSuccess { data ->
                //能否继续加载
                canLoadMore.value = data.page < data.pageCount
                val oldItems = _shareArticles.value ?: emptyList()
                val newItems = data.datas
                //更新数据
                _shareArticles.value = if (page == 0) newItems else oldItems + newItems
                //更新nextPage
                pageNo = page
            }
            result.doOnFailure {
                it.showToast()
            }
        }

    //收藏或取消收藏
    private fun toCollection(item: ShareArticleItem) = launchAsyncRequest {
        if (item.collection.value == true) {
            //取消收藏
            val result = userRepository.cancelArticle(item.entity.id)
            result.doOnSuccess {
                getStringRes(R.string.home_uncollectSuccess).showToast()
                item.collection.value = false
            }
            result.doOnFailure {
                it.showToast()
            }
        } else {
            //收藏
            val result = userRepository.collectInner(item.entity.id)
            result.doOnSuccess {
                getStringRes(R.string.home_collectSuccess).showToast()
                item.collection.value = true
            }
            result.doOnFailure {
                it.showToast()
            }
        }
    }
}