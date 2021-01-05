package com.looptry.db

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.looptry.db.db.AppDb
import com.looptry.db.entity.user.UserInfo
import com.looptry.db.entity.user.UserInfoDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import java.io.IOException
import java.lang.Exception

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    private lateinit var db: AppDb
    private lateinit var userInfoDao: UserInfoDao

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDb::class.java).build()
        userInfoDao = db.getUserInfoDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun useAppContext(): Unit = runBlocking {
        withContext(Dispatchers.Default) {
            val user = UserInfo("1")
            userInfoDao.createOrReplace(user).also {
               Log.e("TAG","$it")
            }
            Log.e("TAG", "${userInfoDao.queryAll()}")
        }
    }
}