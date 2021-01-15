package com.looptry.wanandroid.net.okhttp

import com.looptry.wanandroid.app.LoginManager
import com.looptry.wanandroid.ext.logE
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
class MyCookieJar private constructor() : CookieJar {

    companion object {
        var instance: MyCookieJar? = null
            get() {
                if (field == null) {
                    synchronized(this) {
                        if (field == null) {
                            field = MyCookieJar()
                        }
                    }
                }
                return field
            }
            private set
    }

    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        return LoginManager.cookie
    }

    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        LoginManager.cookie.clear()
        LoginManager.cookie.addAll(cookies)
    }
}