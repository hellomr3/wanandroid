package com.looptry.wanandroid;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Author: mr.3
 * Date:
 * Desc:
 * Modify By:
 * Modify Date:
 */
class FuncHandler implements InvocationHandler {

    Object target;

    FuncHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //print method name and parameter
        System.out.print(target + " " + method.getName() + " ");
        System.out.print("" + args.toString() + " ");
        System.out.println();
        //invoke meta func
        return method.invoke(target, args);
    }
}
