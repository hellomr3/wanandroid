package com.looptry.wanandroid.utils

import com.blankj.utilcode.util.TimeUtils
import com.looptry.wanandroid.ext.logE
import io.microshow.rxffmpeg.RxFFmpegCommandList
import io.microshow.rxffmpeg.RxFFmpegInvoke
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
object WaterUtils {

    suspend fun water(videoPath: String, waterPath: String, outputPath: String) =
        withContext(Dispatchers.Default) {
            //命令
            val commandList = RxFFmpegCommandList()
                .append("-y")
                .append("-i")
                .append(videoPath)
                .append("-i")
                .append(waterPath)
                .append("-filter_complex")
                .append("[0:v]scale=iw:ih[outv0];[1:0]scale=0.0:0.0[outv1];[outv0][outv1]overlay=main_w-overlay_w-10:main_h-overlay_h-10")
                .append("-preset")
                .append("superfast")
                .append(outputPath)
            //同步执行
            RxFFmpegInvoke.getInstance().runCommand(commandList.build(), null)
        }

    suspend fun waterImage(imagePath: String, waterPath: String, outputPath: String) =
        withContext(Dispatchers.Default) {
            //命令
            val commandList = RxFFmpegCommandList()
                .append("-y")
                .append("-i")
                .append(imagePath)
                .append("-i")
                .append(waterPath)
                .append("-filter_complex")
                .append("[0:v]scale=iw:ih[outv0];[1:0]scale=0.0:0.0[outv1];[outv0][outv1]overlay=main_w-overlay_w:main_h-overlay_h")
                .append("-preset")
                .append("superfast")
                .append(outputPath)
            //同步执行
            RxFFmpegInvoke.getInstance().runCommand(commandList.build(), null)
        }

    suspend fun splitByTime(videoPath: String, interval: Long) {
        val info = RxFFmpegInvoke.getInstance().getMediaInfo(videoPath)
        //获取视频的时长
        val duration = info.split(";").find { it.startsWith("duration") }
            ?.split("=")
            ?.getOrNull(1)
            ?.split(" ")
            ?.getOrNull(0)
            ?.toDouble()
            ?.toLong()
            ?: return
        if (interval >= duration) {
            //无需分割
            return
        }
        var start = 0L
        var part = 0
        while (start < duration) {
            val count = if (start + interval <= duration) {
                splitVideo(videoPath, start, interval, part)
                interval
            } else {
                val count = duration - start
                splitVideo(videoPath, start, duration - start, part)
                count
            }
            start += count
            part++
        }
    }

    suspend fun splitVideo(
        videoPath: String,
        start: Long,
        interval: Long,
        part: Int
    ) = withContext(Dispatchers.Default) {
        val startFormat = TimeUtils.millis2String(start, "00:mm:ss")
        val endFormat = TimeUtils.millis2String(interval, "00:mm:ss")
        val src = File(videoPath)
        val srcName = src.name.split(".")[0]
        val dst = File(
            src.parent,
            "${srcName}_part$part.mp4"
        )
        //命令
        val commandList = RxFFmpegCommandList()
            .append("-i")
            .append(videoPath)
            .append("-ss")
            .append(startFormat)
            //-to s——e
            //-t s+e
            .append("-t")
            .append(endFormat)
            .append("-c")
            .append("copy")
            .append(dst.absolutePath)
        //同步执行
        RxFFmpegInvoke.getInstance().runCommand(commandList.build(), null)
        "file src:${dst.absolutePath}".logE()
    }
}