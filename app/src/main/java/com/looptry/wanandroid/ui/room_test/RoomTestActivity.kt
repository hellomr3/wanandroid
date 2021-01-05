package com.looptry.wanandroid.ui.room_test

import android.content.Intent
import androidx.activity.viewModels
import com.looptry.architecture.BR
import com.looptry.architecture.page.DataBindingConfig
import com.looptry.wanandroid.R
import com.looptry.wanandroid.ext.getStringRes
import com.looptry.wanandroid.ext.showToast
import com.looptry.wanandroid.ui.repeat.RepeatActivity
import com.looptry.wanandroid.widget.activity.BaseActivity
import java.util.regex.Pattern

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
class RoomTestActivity : BaseActivity() {

    private val viewModel by viewModels<RoomTestViewModel>()

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(BR.vm, R.layout.activity_room_test, viewModel)
            .addBindingParams(BR.click, ClickProxy())
    }

    inner class ClickProxy {
        fun createUser() {
            val userId = viewModel.userId.value
            if (userId.isNullOrBlank()) {
                "请输入用户Id".showToast()
                return
            }
            if (!Pattern.compile("\\d+").matcher(userId).matches()) {
                "请输入数字".showToast()
                return
            }
            viewModel.createUser(userId.toInt())
        }

        fun createLibrary() {
            val userId = viewModel.userId.value
            val libraryId = viewModel.libraryId.value
            if (userId.isNullOrBlank()) {
                "请输入用户Id".showToast()
                return
            }
            if (libraryId.isNullOrBlank()) {
                "请输入LibraryId".showToast()
                return
            }
            if (!Pattern.compile("\\d+").matcher(userId).matches() ||
                !Pattern.compile("\\d+").matcher(libraryId).matches()
            ) {
                "请输入数字".showToast()
                return
            }
            viewModel.createLibrary(userId.toInt(), libraryId.toInt())
        }

        fun deleteUser() {
            val userId = viewModel.userId.value
            if (userId.isNullOrBlank()) {
                "请输入用户Id".showToast()
                return
            }
            if (!Pattern.compile("\\d+").matcher(userId).matches()) {
                "请输入数字".showToast()
                return
            }
            viewModel.deleteUser(userId.toInt())
        }

        fun deleteLibrary() {
            val userId = viewModel.userId.value
            val libraryId = viewModel.libraryId.value
            if (userId.isNullOrBlank()) {
                "请输入用户Id".showToast()
                return
            }
            if (libraryId.isNullOrBlank()) {
                "请输入LibraryId".showToast()
                return
            }
            if (!Pattern.compile("\\d+").matcher(userId).matches() ||
                !Pattern.compile("\\d+").matcher(libraryId).matches()
            ) {
                "请输入数字".showToast()
                return
            }
            viewModel.deleteLibrary(userId.toInt(), libraryId.toInt())
        }


        fun queryView() {
            viewModel.queryView()
        }

        fun toNext() {
            val intent = Intent(this@RoomTestActivity, RepeatActivity::class.java)
            startActivity(intent)
        }
    }
}