package com.hjq.bar;

import android.view.View;
/**
 *    author : HJQ
 *    github : https://github.com/getActivity/TitleBar
 *    time   : 2018/08/20
 *    desc   : 标题栏点击监听接口
 */
public interface OnTitleBarListener {

    void onLeftClick(View v);

    void onTitleClick(View v);

    void onRightClick(View v);
}