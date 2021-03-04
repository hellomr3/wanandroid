package com.looptry.wanandroid

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.looptry.wanandroid.ui.qa.QAViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest2 {

    lateinit var qaViewModel: QAViewModel

    @Before
    fun createRepository() {
        qaViewModel = QAViewModel()
    }

    @Test
    fun useAppContext() = runBlocking(Dispatchers.Main){

    }
}