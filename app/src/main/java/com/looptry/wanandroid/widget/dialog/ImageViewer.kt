package com.looptry.wanandroid.widget.dialog

import android.content.Context
import android.view.View
import com.looptry.wanandroid.R
import com.lxj.xpopup.impl.FullScreenPopupView

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
class ImageViewer(context: Context) : FullScreenPopupView(context) {



    override fun getImplLayoutId(): Int {
        return R.layout.dialog_image_viewer
    }

    override fun getPopupImplView(): View {
        return super.getPopupImplView()
    }

}