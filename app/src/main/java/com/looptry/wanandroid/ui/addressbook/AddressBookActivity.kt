package com.looptry.wanandroid.ui.addressbook

import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
import com.looptry.architecture.BR
import com.looptry.architecture.page.DataBindingConfig
import com.looptry.wanandroid.R
import com.looptry.wanandroid.databinding.ActivityAddressBookBinding
import com.looptry.wanandroid.ext.logE
import com.looptry.wanandroid.widget.activity.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.abs

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
@Route(path = "/app/AddressBook")
@AndroidEntryPoint
class AddressBookActivity : BaseActivity(), TitleItemDecoration.II {

    private val viewModel by viewModels<AddressBookViewModel>()

    private val binding by lazy {
        mBinding as ActivityAddressBookBinding
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(BR.vm, R.layout.activity_address_book, viewModel)
    }

    override fun initObserver() {
        super.initObserver()
        viewModel.origins.observe(this) {
            val groupMap = it.groupBy { it.typeName }
            val list = mutableListOf<Any>()
            groupMap.keys.toSortedSet()
                .forEach {
                    list.addAll(groupMap[it]!!)
                }
            viewModel.items.update(list)
        }
    }

    override fun requestOnCreate() {
        super.requestOnCreate()
        viewModel.provideContact()
    }

    override fun initView() {
        super.initView()
        binding.content.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = binding.content.layoutManager ?: return
                val position =
                    recyclerView.getChildAdapterPosition(layoutManager.getChildAt(0) ?: return)
                val preData = viewModel.items.getOrNull(position) ?: return
                if (preData is AddressBookViewModel.AddressBook) {
                    if (binding.headerText.text != preData.typeName)
                        binding.headerText.text = preData.typeName
                }
            }
        })
        val decoration = TitleItemDecoration().apply {
            ii = this@AddressBookActivity
        }
        binding.content.addItemDecoration(decoration, 0)
        binding.content.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            ), 1
        )
    }

    override fun checkHead(position: Int): Boolean {
        val datas = viewModel.items
        if (position == 0) {
            if (datas.getOrNull(position) is AddressBookViewModel.AddressBook) {
                return true
            }
        } else {
            val preData = datas[position - 1]
            val data = datas[position]
            if (preData is AddressBookViewModel.AddressBook && data is AddressBookViewModel.AddressBook) {
                if (preData.typeName != data.typeName) {
                    return true
                }
            } else if (data is AddressBookViewModel.AddressBook) {
                return true
            }
        }
        return false
    }

    override fun provideGroupName(position: Int): String {
        val item = viewModel.items.getOrNull(position) ?: return ""
        if (item is AddressBookViewModel.AddressBook) {
            return item.typeName
        }
        return ""
    }
}