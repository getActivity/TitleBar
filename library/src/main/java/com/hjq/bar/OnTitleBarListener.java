package com.hjq.bar;

import androidx.annotation.NonNull;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/TitleBar
 *    time   : 2018/08/20
 *    desc   : 标题栏点击监听接口
 */
public interface OnTitleBarListener {

    /**
     * 左边的标题被点击
     *
     * @param titleBar      标题栏对象（非空）
     */
    default void onLeftClick(@NonNull TitleBar titleBar) {
        // default implementation ignored
    }

    /**
     * 中间的标题被点击
     *
     * @param titleBar      标题栏对象（非空）
     */
    default void onTitleClick(@NonNull TitleBar titleBar) {
        // default implementation ignored
    }

    /**
     * 右边的标题被点击
     *
     * @param titleBar      标题栏对象（非空）
     */
    default void onRightClick(@NonNull TitleBar titleBar) {
        // default implementation ignored
    }
}