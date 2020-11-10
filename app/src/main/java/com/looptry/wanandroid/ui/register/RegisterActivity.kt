package com.looptry.wanandroid.ui.register

import android.content.Context
import android.content.Intent
import androidx.activity.viewModels
import com.looptry.architecture.page.DataBindingConfig
import com.looptry.wanandroid.R
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

        }
    }
}