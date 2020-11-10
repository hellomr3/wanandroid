package com.looptry.wanandroid.ext

import com.blankj.utilcode.util.EncryptUtils

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
fun String.encryptMd5(useLowerCase: Boolean = true): String {
    val md5 = EncryptUtils.encryptMD5ToString(this)
    return if (useLowerCase) {
        md5.toLowerCase()
    } else {
        md5.toUpperCase()
    }

}