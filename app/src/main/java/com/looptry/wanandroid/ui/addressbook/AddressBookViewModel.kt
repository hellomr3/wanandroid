package com.looptry.wanandroid.ui.addressbook

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DiffUtil
import com.looptry.wanandroid.R
import com.looptry.wanandroid.ext.launchAsyncRequest
import kotlinx.coroutines.delay
import me.tatarka.bindingcollectionadapter2.BR
import me.tatarka.bindingcollectionadapter2.ItemBinding
import me.tatarka.bindingcollectionadapter2.OnItemBind
import me.tatarka.bindingcollectionadapter2.collections.AsyncDiffObservableList

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
class AddressBookViewModel : ViewModel() {

    data class AddressBook(val name: String, val number: String) {
        companion object {
            val itemCallback = object : DiffUtil.ItemCallback<AddressBook>() {
                override fun areItemsTheSame(oldItem: AddressBook, newItem: AddressBook): Boolean {
                    return oldItem.name == newItem.name
                }

                override fun areContentsTheSame(
                    oldItem: AddressBook,
                    newItem: AddressBook
                ): Boolean {
                    return oldItem.name == newItem.name && oldItem.number == newItem.number
                }
            }
            val strCallback = object : DiffUtil.ItemCallback<String>() {
                override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                    return newItem == oldItem
                }

                override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                    return newItem == oldItem
                }

            }
        }

        //根据首字母获取分类名
        val typeName: String by lazy {
            name.firstOrNull()?.toUpperCase()?.toString() ?: "A"
        }
    }

    val tag = MutableLiveData<String>()

    val showTag = MutableLiveData<Boolean>(false)

    val origins = MutableLiveData<List<AddressBook>>()

    val items = AsyncDiffObservableList<Any>(object : DiffUtil.ItemCallback<Any>() {
        override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
            return when {
                oldItem is AddressBook && newItem is AddressBook -> {
                    AddressBook.itemCallback.areItemsTheSame(oldItem, newItem)
                }
                oldItem is String && newItem is String -> {
                    AddressBook.strCallback.areItemsTheSame(oldItem, newItem)
                }
                else -> false
            }
        }

        override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
            return when {
                oldItem is AddressBook && newItem is AddressBook -> {
                    AddressBook.itemCallback.areContentsTheSame(oldItem, newItem)
                }
                oldItem is String && newItem is String -> {
                    AddressBook.strCallback.areContentsTheSame(oldItem, newItem)
                }
                else -> false
            }
        }

    })
    val itemBinding = OnItemBind<Any> { itemBinding, position, item ->
        when (item) {
            is AddressBook -> {
                itemBinding.set(BR.item, R.layout.item_address_book)
            }
            is String -> {
                itemBinding.set(BR.item, R.layout.item_nav)
            }
        }
    }

    val navItems = AsyncDiffObservableList<String>(AddressBook.strCallback)
    val navItemBinding = ItemBinding.of<String> { itemBinding, position, item ->
        itemBinding.set(BR.item, R.layout.item_nav)
    }


    fun provideContact() = launchAsyncRequest {
        delay(200)
        val list = mutableListOf<AddressBook>()
            .apply {
                add(AddressBook("Aaaaaa", "13111111111"))
                add(AddressBook("Abaaaa", "13111111111"))
                add(AddressBook("Baaaaa", "13111111111"))
                add(AddressBook("Caaaaa", "13111111111"))
                add(AddressBook("Daaaaa", "13111111111"))
                add(AddressBook("Eaaaaa", "13111111111"))
                add(AddressBook("Faaaaa", "13111111111"))
                add(AddressBook("Gaaaaa", "13111111111"))
                add(AddressBook("Haaaaa", "13111111111"))
                add(AddressBook("Iaaaaa", "13111111111"))
                add(AddressBook("Jaaaaa", "13111111111"))
                add(AddressBook("Kaaaaa", "13111111111"))
                add(AddressBook("Laaaaa", "13111111111"))
                add(AddressBook("Maaaaa", "13111111111"))
                add(AddressBook("Naaaaa", "13111111111"))
                add(AddressBook("Oaaaaa", "13111111111"))
                add(AddressBook("Paaaaa", "13111111111"))
                add(AddressBook("Qaaaaa", "13111111111"))
                add(AddressBook("Raaaaa", "13111111111"))
                add(AddressBook("Saaaaa", "13111111111"))
                add(AddressBook("Taaaaa", "13111111111"))
                add(AddressBook("Uaaaaa", "13111111111"))
                add(AddressBook("Vaaaaa", "13111111111"))
                add(AddressBook("Waaaaa", "13111111111"))
                add(AddressBook("Xaaaaa", "13111111111"))
                add(AddressBook("Yaaaaa", "13111111111"))
                add(AddressBook("Zaaaaa", "13111111111"))
            }

        origins.value = list
    }
}