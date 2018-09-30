package com.hjq.bar;

/**
 *    author : HJQ
 *    github : https://github.com/getActivity/TitleBar
 *    time   : 2018/08/20
 *    desc   : 默认参数接口
 */
public interface ITitleBarStyle {

    int getTitleBarHeight();//标题栏高度
    int getBackgroundColor();//背景颜色
    int getBackIconResource();//返回按钮图标

    int getLeftViewColor();//左边文本颜色
    int getTitleViewColor();//标题文本颜色
    int getRightViewColor();//右边文本颜色

    float getLeftViewSize();//左边文本大小
    float getTitleViewSize();//标题文本大小
    float getRightViewSize();//右边文本大小

    boolean getLineVisibility();//分割线是否可见
    int getLineBackgroundColor();//分割线背景颜色

    int getLeftViewBackground();//左边背景资源
    int getRightViewBackground();//右边背景资源
}
