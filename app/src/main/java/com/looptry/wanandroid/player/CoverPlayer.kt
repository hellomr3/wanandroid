package com.looptry.wanandroid.player

import android.content.Context
import android.util.AttributeSet
import android.view.Surface
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.request.RequestOptions
import com.looptry.wanandroid.R
import com.looptry.wanandroid.app.GlideApp
import com.shuyu.gsyvideoplayer.utils.GSYVideoType
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer
import com.shuyu.gsyvideoplayer.video.base.GSYVideoView

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
class CoverPlayer(context: Context, attr: AttributeSet?) : StandardGSYVideoPlayer(context, attr) {

    lateinit var mCoverImage: ImageView

    override fun getLayoutId(): Int {
        return R.layout.video_layout_cover
    }

    override fun init(context: Context?) {
        super.init(context)
        mCoverImage = findViewById(R.id.thumbImage)
        //显示thumb
        if (mThumbImageViewLayout != null && (mCurrentState == -1 || mCurrentState == CURRENT_STATE_NORMAL || mCurrentState == CURRENT_STATE_ERROR)) {
            mThumbImageViewLayout.visibility = VISIBLE
        }
    }

    //加载封面
    fun loadCoverImage(url: String, placeholder: Int) {
        GlideApp.with(context)
            .setDefaultRequestOptions(
                RequestOptions().frame(1000000)
                    .centerCrop()
                    .error(placeholder)
                    .placeholder(placeholder)
            )
            .load(url)
            .into(mCoverImage)
    }

    override fun updateStartImage() {
        super.updateStartImage()
        if (mStartButton is ImageView) {
            val imageView = mStartButton as ImageView
            when (mCurrentState) {
                GSYVideoView.CURRENT_STATE_PLAYING -> {
                    imageView.visibility = INVISIBLE
//                    imageView.setImageResource(R.drawable.ic_pause)
                }
                GSYVideoView.CURRENT_STATE_ERROR -> {
                    imageView.setImageResource(R.drawable.ic_play)
                }
                else -> {
                    imageView.setImageResource(R.drawable.ic_play)
                }
            }
        }
    }

    /******************* 下方两个重载方法，在播放开始前不屏蔽封面，不需要可屏蔽 ********************/
    override fun onSurfaceUpdated(surface: Surface?) {
        super.onSurfaceUpdated(surface)
        if (mThumbImageViewLayout != null && mThumbImageViewLayout.visibility == VISIBLE) {
            mThumbImageViewLayout.visibility = INVISIBLE
        }
    }

    override fun setViewShowState(view: View?, visibility: Int) {
        if (view === mThumbImageViewLayout && visibility != VISIBLE) {
            return
        }
        super.setViewShowState(view, visibility)
    }

    override fun onSurfaceAvailable(surface: Surface?) {
        super.onSurfaceAvailable(surface)
        if (GSYVideoType.getRenderType() != GSYVideoType.TEXTURE) {
            if (mThumbImageViewLayout != null && mThumbImageViewLayout.visibility == VISIBLE) {
                mThumbImageViewLayout.visibility = INVISIBLE
            }
        }
    }
}