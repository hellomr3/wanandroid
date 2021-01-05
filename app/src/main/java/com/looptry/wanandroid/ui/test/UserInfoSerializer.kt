package com.looptry.wanandroid.ui.test

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.createDataStore
import com.google.protobuf.InvalidProtocolBufferException
import com.looptry.protobuf.entity.message
import java.io.InputStream
import java.io.OutputStream

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
object UserInfoSerializer : Serializer<message.UserInfo> {

    override val defaultValue: message.UserInfo
        get() = message.UserInfo.getDefaultInstance()

    override fun readFrom(input: InputStream): message.UserInfo {
        try {
            return message.UserInfo.parseFrom(input)
        } catch (e: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto", e)
        }
    }

    override fun writeTo(t: message.UserInfo, output: OutputStream) {
        t.writeTo(output)
    }
}

val Context.userInfoDataStore: DataStore<message.UserInfo>
    get() {
        return this.createDataStore("UserInfo", UserInfoSerializer)
    }