package com.looptry.wanandroid;

import java.util.Collection;

interface MouseListener {
     void func1(String event);

    default void mousePressed(String event) {
    }

    default void mouseReleased(String event) {
    }

    default void mouseEntered(String event) {
    }

    default void mouseExited(String event) {
    }
}