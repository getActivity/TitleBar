package com.hjq.bar;

import android.annotation.ColorInt;
import android.annotation.DrawableRes;

/**
 *    author : HJQ
 *    github : https://github.com/getActivity/TitleBar
 *    time   : 2018/08/20
 *    desc   : 默认参数接口
 */
public interface ITitleBarStyle {

    @ColorInt
    int getBackgroundColor();//背景颜色
    @DrawableRes
    int getBackIconResource();//返回按钮图标

    @ColorInt
    int getLeftViewColor();//左边文本颜色
    @ColorInt
    int getTitleViewColor();//标题文本颜色
    @ColorInt
    int getRightViewColor();//右边文本颜色

    float getLeftViewSize();//左边文本大小
    float getTitleViewSize();//标题文本大小
    float getRightViewSize();//右边文本大小

    @DrawableRes
    int getLeftViewBackground();//左边背景资源
    @DrawableRes
    int getRightViewBackground();//右边背景资源

    boolean getLineVisibility();//分割线是否可见
    @ColorInt
    int getLineBackgroundColor();//分割线背景颜色
}
