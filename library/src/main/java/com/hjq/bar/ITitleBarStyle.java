package com.hjq.bar;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/TitleBar
 *    time   : 2020/08/16
 *    desc   : 标题栏样式接口
 */
public interface ITitleBarStyle {

    /**
     * 创建左标题 View
     */
    TextView createLeftView(Context context);

    /**
     * 创建中间标题 View
     */
    TextView createTitleView(Context context);

    /**
     * 获取右标题 View
     */
    TextView createRightView(Context context);

    /**
     * 获取分割线 View
     */
    View createLineView(Context context);

    /**
     * 获取标题栏背景
     */
    Drawable getTitleBarBackground(Context context);

    /**
     * 获取左边标题的按钮背景
     */
    Drawable getLeftTitleBackground(Context context);

    /**
     * 获取右边标题的按钮背景
     */
    Drawable getRightTitleBackground(Context context);

    /**
     * 获取默认按钮图片
     */
    Drawable getBackButtonDrawable(Context context);

    /**
     * 获取子控件的水平内间距
     */
    int getChildHorizontalPadding(Context context);

    /**
     * 获取子控件的垂直内间距
     */
    int getChildVerticalPadding(Context context);

    /**
     * 获取左边标题的默认字体颜色
     */
    int getLeftTitleColor(Context context);

    /**
     * 获取中间标题的默认字体颜色
     */
    int getTitleTitleColor(Context context);

    /**
     * 获取右边标题的默认字体颜色
     */
    int getRightTitleColor(Context context);

    /**
     * 获取左边标题的默认字体大小
     */
    int getLeftTitleSize(Context context);

    /**
     * 获取中间标题的默认字体大小
     */
    int getTitleTitleSize(Context context);

    /**
     * 获取右边标题的默认字体大小
     */
    int getRightTitleSize(Context context);

    /**
     * 获取左边标题的图标默认重心
     */
    int getLeftIconGravity(Context context);

    /**
     * 获取中间标题的图标默认重心
     */
    int getTitleIconGravity(Context context);

    /**
     * 获取右边标题的图标默认重心
     */
    int getRightIconGravity(Context context);

    /**
     * 获取左边标题的图标默认间距
     */
    int getLeftIconPadding(Context context);

    /**
     * 获取中间标题的图标默认间距
     */
    int getTitleIconPadding(Context context);

    /**
     * 获取右边标题的图标默认间距
     */
    int getRightIconPadding(Context context);

    /**
     * 获取左边标题的图标默认宽度
     */
    int getLeftIconWidth(Context context);

    /**
     * 获取中间标题的图标默认宽度
     */
    int getTitleIconWidth(Context context);

    /**
     * 获取右边标题的图标默认宽度
     */
    int getRightIconWidth(Context context);

    /**
     * 获取左边标题的图标默认高度
     */
    int getLeftIconHeight(Context context);

    /**
     * 获取中间标题的图标默认高度
     */
    int getTitleIconHeight(Context context);

    /**
     * 获取右边标题的图标默认高度
     */
    int getRightIconHeight(Context context);

    /**
     * 获取分割线默认大小
     */
    int getLineSize(Context context);

    /**
     * 获取分割线默认背景
     */
    Drawable getLineDrawable(Context context);
}