package com.looptry.wanandroid.ui.login

import android.content.Context
import android.content.Intent
import android.text.method.LinkMovementMethod
import androidx.activity.viewModels
import com.alibaba.android.arouter.facade.annotation.Route
import com.looptry.architecture.page.DataBindingConfig
import com.looptry.wanandroid.R
import com.looptry.wanandroid.databinding.ActivityLoginBinding
import com.looptry.wanandroid.ext.getStringRes
import com.looptry.wanandroid.ext.showToast
import com.looptry.wanandroid.route.RouteConfig
import com.looptry.wanandroid.widget.activity.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import me.tatarka.bindingcollectionadapter2.BR

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
@AndroidEntryPoint
@Route(path = RouteConfig.PAGE_APP_LOGIN)
class LoginActivity : BaseActivity() {

    companion object {
        fun navigation(context: Context) {
            val intent = Intent(context, LoginActivity::class.java)
            context.startActivity(intent)
        }
    }

    private val viewModel by viewModels<LoginViewModel>()

    private val binding by lazy {
        mBinding as ActivityLoginBinding
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(BR.vm, R.layout.activity_login, viewModel)
            .addBindingParams(BR.click, ClickProxy())
    }

    override fun initView() {
        super.initView()
        //使Spannable点击事件生效
        binding.toRegister.movementMethod = LinkMovementMethod.getInstance()
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