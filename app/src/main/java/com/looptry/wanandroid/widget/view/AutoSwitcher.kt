package com.looptry.wanandroid.widget.view

import android.content.Context
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.AttributeSet
import android.widget.TextSwitcher
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.looptry.wanandroid.ext.logE
import java.util.*


/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
class AutoSwitcher(context: Context, attrs: AttributeSet?) : TextSwitcher(context, attrs),
    LifecycleObserver {

    private val lists = mutableListOf<String>()

    private val handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                1001 -> {
                    (msg.obj as? Int)?.let { index ->
                        (nextView as? TextView)?.let {
                            it.text = lists[index]
                        }
                        showNext()
                    }
                }
            }
        }
    }

    private var tick = 0

    private var timer: Timer = Timer()

    private var timerTask: TimerTask? = null

    public fun updateList(list: List<String>) {
        lists.clear()
        lists.addAll(list)
        start()
    }

    /**
     * 执行定时任务
     */
    private fun start() {
        val size = lists.size
        if (size == 0) return
        setCurrentText(lists[tick % size])
        timerTask = object : TimerTask() {
            override fun run() {
                val msg = Message()
                msg.what = 1001
                msg.obj = ++tick % size
                handler.sendMessage(msg)
            }
        }
        timer.schedule(timerTask, 4000, 4000)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        this.timerTask?.cancel()
        start()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onStop() {
        this.timerTask?.cancel()
    }
}