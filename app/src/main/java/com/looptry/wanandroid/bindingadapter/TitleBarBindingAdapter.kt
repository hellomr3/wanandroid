package com.looptry.wanandroid.bindingadapter

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import com.hjq.bar.OnTitleBarListener
import com.hjq.bar.TitleBar

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
@BindingAdapter(value = ["useFinish"], requireAll = false)
fun TitleBar.bind(
    finish: Boolean?,
) {
    //设置点击事件
    setOnTitleBarListener(object : OnTitleBarListener {
        override fun onLeftClick(v: View?) {
            if (finish == true) {
                (v?.context as? AppCompatActivity)?.let {
                    it.finish()
                }
            }
        }

        override fun onTitleClick(v: View?) {

        }

        override fun onRightClick(v: View?) {

        }

    })
}