package com.looptry.wanandroid.app

import android.content.Context
import android.content.Intent
import com.looptry.wanandroid.ui.login.LoginActivity

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
class LoginManager {

    companion object {

        fun toLogin(context: Context) {
            LoginActivity.navigation(context)
        }
    }
}