package com.looptry.wanandroid.route.degrade

import android.content.Context
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.facade.service.DegradeService
import com.looptry.wanandroid.app.LoginManager
import com.looptry.wanandroid.route.RouteConfig

/**
 * Author: mr.3
 * Date:
 * Desc: 处理跳转时页面Lost
 * Modify By:
 * Modify Date:
 */
@Route(path = "/app/degrade")
class DegradeServiceImpl : DegradeService {
    override fun init(context: Context?) {

    }

    override fun onLost(context: Context, postcard: Postcard) {

    }

}