package com.looptry.wanandroid.ui.home

import android.content.Context
import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.looptry.architecture.request.doOnFailure
import com.looptry.architecture.request.doOnSuccess
import com.looptry.wanandroid.BR
import com.looptry.wanandroid.R
import com.looptry.wanandroid.app.LoginManager
import com.looptry.wanandroid.ext.launchAsync
import com.looptry.wanandroid.ext.launchAsyncRequest
import com.looptry.wanandroid.ext.showToast
import com.looptry.wanandroid.model.entity.article.ShareArticle
import com.looptry.wanandroid.model.entity.banner.BannerInfo
import com.looptry.wanandroid.model.mapper.ShareArticle2ShareArticleItem
import com.looptry.wanandroid.model.view.ShareArticleItem
import com.looptry.wanandroid.repository.IRequest
import dagger.hilt.android.qualifiers.ActivityContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
    @ActivityContext private val context: Context
) : ViewModel() {

    val nextPage = MutableLiveData(0)

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

    fun test() = launchAsync{
        delay(1000)
        launch {
            throw Throwable("cc")
        }
        withContext(Dispatchers.Default) {
            delay(10)
            throw Throwable("abc")
        }
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
                //停止刷新、加载
                finishAll.value = true
                val oldItems = _shareArticles.value ?: emptyList()
                val newItems = data.datas
                //更新数据
                _shareArticles.value = if (page == 0) newItems else oldItems + newItems
                //更新nextPage
                nextPage.value = page + 1
            }
            result.doOnFailure {
                it.showToast()
            }
        }

    //收藏或取消收藏
    private fun toCollection(item: ShareArticleItem) = viewModelScope.launch {
        //检查登录 如果没用登陆去登录页
        LoginManager.toLogin(context)
//        delay(200)
//        item.collection.value = !item.collection.value!!
    }
}