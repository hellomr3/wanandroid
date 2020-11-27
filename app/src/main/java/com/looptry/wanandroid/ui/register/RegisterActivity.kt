package com.looptry.wanandroid.ui.register

import android.content.Context
import android.content.Intent
import androidx.activity.viewModels
import com.looptry.architecture.page.DataBindingConfig
import com.looptry.wanandroid.R
import com.looptry.wanandroid.ext.getStringRes
import com.looptry.wanandroid.ext.showToast
import com.looptry.wanandroid.widget.activity.BaseActivity
import me.tatarka.bindingcollectionadapter2.BR

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
class RegisterActivity : BaseActivity() {
    companion object {
        fun navigation(context: Context) {
            val intent = Intent(context, RegisterActivity::class.java)
            context.startActivity(intent)
        }
    }

    private val viewModel by viewModels<RegisterViewModel>()

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(BR.vm, R.layout.activity_register, viewModel)
            .addBindingParams(BR.click, ClickProxy())
    }

    inner class ClickProxy {
        fun register() {
            val username = viewModel.inputUserName.value
            val password = viewModel.inputPassword.value
            val repassword = viewModel.inputRePassword.value
            if (username.isNullOrBlank()) {
                getStringRes(R.string.register_username_hint).showToast()
                return
            }
            if (password.isNullOrBlank()) {
                getStringRes(R.string.register_password_hint).showToast()
                return
            }
            if (repassword.isNullOrBlank()) {
                getStringRes(R.string.register_repassword_hint).showToast()
                return
            }
            viewModel.register(username, password, repassword)
        }
    }
}