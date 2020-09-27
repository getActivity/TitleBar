package com.hjq.bar;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;


/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/TitleBar
 *    time   : 2020/08/16
 *    desc   : 默认初始化器
 */
public interface ITitleBarInitializer {

    /**
     * 获取左标题 View
     */
    TextView getLeftView(Context context);

    /**
     * 获取中间标题 View
     */
    TextView getTitleView(Context context);

    /**
     * 获取右标题 View
     */
    TextView getRightView(Context context);

    /**
     * 获取分割线 View
     */
    View getLineView(Context context);

    /**
     * 获取标题栏背景
     */
    Drawable getBackgroundDrawable(Context context);

    /**
     * 获取子控件的水平内间距
     */
    int getHorizontalPadding(Context context);

    /**
     * 获取子控件的垂直内间距
     */
    int getVerticalPadding(Context context);
}