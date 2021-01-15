package com.looptry.wanandroid.ui.water

import android.content.Intent
import android.graphics.Bitmap
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.blankj.utilcode.constant.PermissionConstants
import com.blankj.utilcode.util.FileUtils
import com.blankj.utilcode.util.ImageUtils
import com.blankj.utilcode.util.PermissionUtils
import com.blankj.utilcode.util.UriUtils
import com.looptry.architecture.BR
import com.looptry.architecture.page.DataBindingConfig
import com.looptry.wanandroid.R
import com.looptry.wanandroid.databinding.ActivityWaterBinding
import com.looptry.wanandroid.ext.getDrawableRes
import com.looptry.wanandroid.ext.logE
import com.looptry.wanandroid.utils.WaterUtils
import com.looptry.wanandroid.widget.activity.BaseActivity
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureConfig
import com.luck.picture.lib.config.PictureMimeType
import com.shuyu.gsyvideoplayer.GSYVideoManager
import com.shuyu.gsyvideoplayer.model.VideoOptionModel
import io.microshow.rxffmpeg.RxFFmpegInvoke
import kotlinx.coroutines.launch
import tv.danmaku.ijk.media.player.IjkMediaPlayer
import java.io.File


/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
class WaterActivity : BaseActivity() {

    companion object {
        const val SRC_VIDEO = 1001
        const val SRC_IMAGE = 1002
        const val TRAN_DEMO_PATH =
            "/storage/emulated/0/HuNanShinyP/VideoRecords/43050001981328000162_20210113103228"
    }

    private val viewModel by viewModels<WaterViewModel>()

    private val binding by lazy {
        mBinding as ActivityWaterBinding
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(BR.vm, R.layout.activity_water, viewModel)
    }

    private val permissions = listOf(PermissionConstants.CAMERA, PermissionConstants.STORAGE)

    override fun initView() {
        super.initView()
        binding.selectVideo.setOnClickListener {
            if (PermissionUtils.isGranted(*permissions.toTypedArray())) {
                selectVideo()
            } else {
                PermissionUtils.permission(*permissions.toTypedArray())
                    .callback { isAllGranted, granted, deniedForever, denied ->
                        if (isAllGranted) {
                            selectVideo()
                        } else {
                            PermissionUtils.launchAppDetailsSettings()
                        }
                    }
                    .request()
            }
        }

        binding.selectImage.setOnClickListener {
            selectImage()
        }

        binding.createWaterImage.setOnClickListener {
            val file = File(
                "/storage/emulated/0/Pictures",
                "water.png"
            )
            FileUtils.createFileByDeleteOldFile(
                file
            )
            val bitmap = ImageUtils.drawable2Bitmap(getDrawableRes(R.drawable.temp1))
            val drawable = WaterDrawable(bitmap, "我是你的昵称")
            val bytes = ImageUtils.drawable2Bytes(drawable, Bitmap.CompressFormat.PNG, 100)
            file.writeBytes(bytes)
            "文件创建成功:${file.absolutePath}".logE()
            viewModel.waterFile.value = file.absolutePath
            binding.waterImage.setImageDrawable(drawable)
        }

        binding.createTempFile.setOnClickListener {
            val srcVideo = viewModel.srcVideo.value ?: return@setOnClickListener
            val file = File(
                File(srcVideo).parent,
                "trans.mp4"
            )
            FileUtils.createFileByDeleteOldFile(
                file
            )
            viewModel.tempFile.value = file.absolutePath
            "create temp:${file.absolutePath}".logE()
        }
        binding.start.setOnClickListener {
            //check
            val srcVideo = viewModel.srcVideo.value ?: return@setOnClickListener
            val water = viewModel.waterFile.value ?: return@setOnClickListener
            val tempFile = viewModel.tempFile.value ?: return@setOnClickListener
            lifecycleScope.launch {
                WaterUtils.water(srcVideo, water, tempFile)
            }
        }

        binding.split.setOnClickListener {
            //check
//            val srcVideo = viewModel.srcVideo.value ?: return@setOnClickListener
            val srcVideo =
                "/storage/emulated/0/HuNanShinyP/VideoRecords/43050001981328000162_20210113103228"
            val uri = UriUtils.file2Uri(File(srcVideo))
            val file = UriUtils.uri2File(uri)
            "uri:${uri},file:${file.absolutePath}".logE()
            RxFFmpegInvoke.getInstance().getMediaInfo(file.absolutePath)
                .also {
                    "info:$it".logE()
                }
//            val uri = UriUtils.file2Uri(File(srcVideo)).toString()
//            "uri:$uri".logE()
//            lifecycleScope.launch {
//                WaterUtils.splitByTime(srcVideo, 10 * 1000)
//            }
        }

        binding.createWaterTransImage.setOnClickListener {
            val file = File(
                "/storage/emulated/0/Pictures",
                "trans.png"
            )
            FileUtils.createFileByDeleteOldFile(
                file
            )
            "Trans文件创建成功:${file.absolutePath}".logE()
            viewModel.transImageFile.value = file.absolutePath
        }

        binding.startWaterImage.setOnClickListener {
            //check
            val srcImage = viewModel.srcImage.value ?: return@setOnClickListener
            val srcWater = viewModel.waterFile.value ?: return@setOnClickListener
            val tempFile = viewModel.transImageFile.value ?: return@setOnClickListener
            lifecycleScope.launch {
                WaterUtils.waterImage(srcImage, srcWater, tempFile)
                "dd:$tempFile".logE()
            }
        }
    }

    private fun selectVideo() {
        PictureSelector.create(this)
            .openGallery(PictureMimeType.ofVideo())
            .imageEngine(GlideEngine)
            .selectionMode(PictureConfig.SINGLE)
            .isCompress(true)
            .withAspectRatio(1, 1)
            .forResult(SRC_VIDEO)
    }

    private fun selectImage() {
        PictureSelector.create(this)
            .openGallery(PictureMimeType.ofImage())
            .imageEngine(GlideEngine)
            .selectionMode(PictureConfig.SINGLE)
            .isCompress(true)
            .withAspectRatio(1, 1)
            .forResult(SRC_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            when (requestCode) {
                SRC_VIDEO -> {
                    val locals = PictureSelector.obtainMultipleResult(data)
                    locals?.lastOrNull()?.let {
                        viewModel.srcVideo.value = it.path
                        "select src:${it.path}".logE()
                    }
                }
                SRC_IMAGE -> {
                    val locals = PictureSelector.obtainMultipleResult(data)
                    locals?.lastOrNull()?.let {
                        viewModel.srcImage.value = it.path
                        "select srcImage:${it.path}".logE()
                    }
                }
            }
        }
    }
}