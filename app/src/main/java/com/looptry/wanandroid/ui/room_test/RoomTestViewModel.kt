package com.looptry.wanandroid.ui.room_test

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.looptry.db.entity.library.Library
import com.looptry.db.entity.library.LibraryById
import com.looptry.db.entity.library.LibraryDao
import com.looptry.db.entity.user.UserInfo
import com.looptry.db.entity.user.UserInfoById
import com.looptry.db.entity.user.UserInfoDao
import com.looptry.wanandroid.ext.launchAsyncRequest
import com.looptry.wanandroid.ext.logE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.math.abs
import kotlin.random.Random

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
class RoomTestViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val userInfoDao: UserInfoDao,
    private val libraryDao: LibraryDao
) : ViewModel() {

    val userId = savedStateHandle.getLiveData<String>("userId")

    val libraryId = savedStateHandle.getLiveData<String>("libraryId")

    fun createUser(id: Int) = launchAsyncRequest {
        //age
        val age = abs(Random(System.currentTimeMillis()).nextInt()) % 100
        "$id:当前年龄:$age".logE()
        //构建用户信息
        val info = UserInfo(id = "用户$id", "姓名$id", age = age)
        withContext(Dispatchers.Default) {
            userInfoDao.createOrReplace(info)
        }
    }

    fun deleteUser(id: Int) = launchAsyncRequest {
        withContext(Dispatchers.Default) {
            userInfoDao.deleteById(UserInfoById("用户$id"))
        }
    }

    fun createLibrary(userId: Int, libraryId: Int) = launchAsyncRequest {
        val library = Library(libraryId, "用户$userId", "$libraryId+1")
        withContext(Dispatchers.Default) {
            libraryDao.createOrReplace(library)
        }
    }

    fun deleteLibrary(userId: Int, libraryId: Int) = launchAsyncRequest {
        withContext(Dispatchers.Default) {
            libraryDao.deleteById(LibraryById(libraryId))
        }
    }

    fun queryView() = launchAsyncRequest {
        withContext(Dispatchers.Default) {
            userInfoDao.queryAll().also {
                "$it".logE()
            }
        }
    }
}