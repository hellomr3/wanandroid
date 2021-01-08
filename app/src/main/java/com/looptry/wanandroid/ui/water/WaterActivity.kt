package com.looptry.wanandroid.ui.water

import android.content.Intent
import android.graphics.Bitmap
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.blankj.utilcode.constant.PermissionConstants
import com.blankj.utilcode.util.FileUtils
import com.blankj.utilcode.util.ImageUtils
import com.blankj.utilcode.util.PermissionUtils
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
import kotlinx.coroutines.launch
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