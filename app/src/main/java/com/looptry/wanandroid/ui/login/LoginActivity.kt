package com.looptry.wanandroid.ui.login

import android.content.Context
import android.content.Intent
import android.text.method.LinkMovementMethod
import androidx.activity.viewModels
import com.looptry.architecture.page.DataBindingConfig
import com.looptry.wanandroid.R
import com.looptry.wanandroid.ext.getStringRes
import com.looptry.wanandroid.ext.showToast
import com.looptry.wanandroid.widget.activity.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_login.view.*
import me.tatarka.bindingcollectionadapter2.BR

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
@AndroidEntryPoint
class LoginActivity : BaseActivity() {

    companion object {
        fun navigation(context: Context) {
            val intent = Intent(context, LoginActivity::class.java)
            context.startActivity(intent)
        }
    }

    private val viewModel by viewModels<LoginViewModel>()

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(BR.vm, R.layout.activity_login, viewModel)
            .addBindingParams(BR.click, ClickProxy())
    }

    override fun initView() {
        super.initView()
        //使Spannable点击事件生效
        mBinding.root.toRegister.movementMethod = LinkMovementMethod.getInstance()
    }

    inner class ClickProxy {
        fun toLogin() {
            val username = viewModel.inputUserName.value
            val password = viewModel.inputPassword.value
            if (username.isNullOrBlank()) {
                getStringRes(R.string.login_username_hint).showToast()
                return
            }
            if (password.isNullOrBlank()) {
                getStringRes(R.string.login_password_hint).showToast()
                return
            }
            viewModel.login(username, password)
        }

    }
}