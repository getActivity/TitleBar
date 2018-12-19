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
     *
     * @param v     被点击的左项View
     */
    void onLeftClick(View v);

    /**
     * 标题被点击
     *
     * @param v     被点击的标题View
     */
    void onTitleClick(View v);

    /**
     * 右项被点击
     *
     * @param v     被点击的右项View
     */
    void onRightClick(View v);
}