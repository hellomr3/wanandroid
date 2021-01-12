package com.looptry.wanandroid

import org.junit.Test
import java.util.regex.Pattern

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
class FlowTest {


    @Test
    fun doIt() {
        val info =
            "major_brand=mp42;minor_version=0;compatible_brands=isommp42;creation_time=2020-11-04T09:36:47.000000Z;location=+30.5924+104.0813/;location-eng=+30.5924+104.0813/;com.android.version=9;url=/storage/emulated/0/DCIM/Camera/VID_20201104_17362658.mp4;iformat_name=mov,mp4,m4a,3gp,3g2,mj2;iformat_long_name=(null);bit_rate=8884.275000 kbs;duration=15040.000000 ms;filesize=235.06 KB;protocol_whitelist=file,crypto;protocol_blacklist=(null);max_ts_probe=50;max_interleave_delta=10000000;max_picture_buffer=3041280 Bytes;videostream_codecpar_codec_type=video;videostream_avcodocname=h264;videostream_profilestring=(null);videostream_codec_fourcc=avc1;pix_fmt_name=yuvj420p;videostream_nb_frames=449;videostream_codecpar_width=1280;videostream_codecpar_height=720;videostream_sample_aspect_ratio_num=1;videostream_sample_aspect_ratio_den=1;display_aspect_ratio_num=16;display_aspect_ratio_den=9;videostream_r_frame_rate=120.000000 fps;videostream_avg_frame_rate=29.990983 fps;videostream_codecpar_bits_per_raw_sample=8 bits;videostream_codecpar_bits_per_coded_sample=24 bits;videostream_codecpar_bit_rate=8691.223000 kbps;videostream_time_base_num=1;videostream_time_base_den=90000;videostream_time_base=90000.000000;videostream_duration=14971.166667;videostream_codec_time_base=180000.000000;videostream_size=235.06 KB;audiostream_codecpar_codec_type=audio;audiostream_size=235.06 KB;audiostream_duration=15040.000000 ms;audiostream_codecpar_bit_rate=128.035000 kbps;audiostream_codecpar_sample_rate=48000 Hz;audiostream_codecpar_channels=2;audiostream_avcodocname=aac;audiostream_profilestring=(null);audiostream_codec_fourcc=mp4a"
        val map = mutableMapOf<String, String>()
        info.split(";")
            .forEach {
                val value = it.split("=")
                val key = value[0]
                val v = value[1]
                map[key] = v
            }
        val duration = map["duration"]?.split(" ")?.get(0)?.toDouble()?.toLong()
        println(duration)
    }


}