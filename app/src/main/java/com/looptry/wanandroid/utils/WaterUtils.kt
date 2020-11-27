package com.looptry.wanandroid.utils

import io.microshow.rxffmpeg.RxFFmpegCommandList
import io.microshow.rxffmpeg.RxFFmpegInvoke
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

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
                .append("[0:v]scale=iw:ih[outv0];[1:0]scale=0.0:0.0[outv1];[outv0][outv1]overlay=0:0")
                .append("-preset")
                .append("superfast")
                .append(outputPath)
                .append(waterPath)

            //同步执行
            RxFFmpegInvoke.getInstance().runCommand(commandList.build(), null)
        }
}