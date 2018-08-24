package com.hjq.bar;

import android.view.View;

/**
 *    author : HJQ
 *    github : https://github.com/getActivity/TitleBar
 *    time   : 2018/08/20
 *    desc   : 标题栏点击监听接口
 */
public interface OnTitleBarListener {

    /**
     * 左项被点击
     */
    void onLeftClick(View v);

    /**
     * 标题被点击
     */
    void onTitleClick(View v);

    /**
     * 右项被点击
     */
    void onRightClick(View v);
}