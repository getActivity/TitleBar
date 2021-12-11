package com.hjq.bar;

import android.view.View;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/TitleBar
 *    time   : 2018/08/20
 *    desc   : 标题栏点击监听接口
 */
public interface OnTitleBarListener {

    /**
     * 左项被点击
     */
    default void onLeftClick(TitleBar titleBar) {}

    /**
     * 标题被点击
     */
    default void onTitleClick(TitleBar titleBar) {}

    /**
     * 右项被点击
     */
    default void onRightClick(TitleBar titleBar) {}
}