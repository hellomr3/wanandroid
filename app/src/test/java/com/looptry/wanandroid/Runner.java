package com.looptry.wanandroid;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
class Runner {

    public void run() {
        Object[] elements = new Object[100];
        Class[] classes = new Class[]{Comparable.class};
        for (int i = 0; i < 100; i++) {
            int v = i + 1;
            elements[i] = Proxy.newProxyInstance(null, classes, new FuncHandler(v));
        }

        //random integer
        Integer key = new Random().nextInt(elements.length) + 1;
        System.out.println("key is :" + key);
        int result = Arrays.binarySearch(elements, key);
        System.out.println("result:" + result);

        if (result > 0) System.out.println(elements[result]);
    }

    void t() throws Throwable {
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {
                Logger.getGlobal().info(e.getMessage());
                Logger.getGlobal().info(e.getMessage());
                Logger.getGlobal().info(e.getMessage());
            }
        });
        throw new Throwable("This is msg");

    }

}
