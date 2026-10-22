package com.hjq.bar;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/TitleBar
 *    time   : 2020/08/16
 *    desc   : 标题栏样式接口
 */
public interface ITitleBarStyle {

    /**
     * 创建标题 View
     */
    @NonNull
    TextView createTitleView(@NonNull Context context);

    /**
     * 创建左标题 View
     */
    @NonNull
    TextView createLeftView(@NonNull Context context);

    /**
     * 获取右标题 View
     */
    @NonNull
    TextView createRightView(@NonNull Context context);

    /**
     * 获取分割线 View
     */
    @NonNull
    View createLineView(@NonNull Context context);

    /**
     * 获取标题栏背景
     */
    @Nullable
    Drawable getTitleBarBackground(@NonNull Context context);

    /**
     * 获取左标题的按钮背景
     */
    @Nullable
    Drawable getLeftTitleBackground(@NonNull Context context);

    /**
     * 获取右标题的按钮背景
     */
    @Nullable
    Drawable getRightTitleBackground(@NonNull Context context);

    /**
     * 获取左标题的按钮前景
     */
    @Nullable
    Drawable getLeftTitleForeground(@NonNull Context context);

    /**
     * 获取右标题的按钮前景
     */
    @Nullable
    Drawable getRightTitleForeground(@NonNull Context context);

    /**
     * 获取默认按钮图片
     */
    @Nullable
    Drawable getBackButtonDrawable(@NonNull Context context);

    /**
     * 获取左标题子控件的水平内间距
     */
    @Px
    int getLeftHorizontalPadding(@NonNull Context context);

    /**
     * 获取标题子控件的水平内间距
     */
    @Px
    int getTitleHorizontalPadding(@NonNull Context context);

    /**
     * 获取右标题子控件的水平内间距
     */
    @Px
    int getRightHorizontalPadding(@NonNull Context context);

    /**
     * 获取子控件的垂直内间距
     */
    @Px
    int getChildVerticalPadding(@NonNull Context context);

    /**
     * 获取标题的默认文本
     */
    @Nullable
    CharSequence getTitle(@NonNull Context context);

    /**
     * 获取左标题的默认文本
     */
    @Nullable
    CharSequence getLeftTitle(@NonNull Context context);

    /**
     * 获取右边标题的默认文本
     */
    @Nullable
    CharSequence getRightTitle(@NonNull Context context);

    /**
     * 获取标题的默认字体颜色
     */
    @Nullable
    ColorStateList getTitleColor(@NonNull Context context);

    /**
     * 获取左标题的默认字体颜色
     */
    @Nullable
    ColorStateList getLeftTitleColor(@NonNull Context context);

    /**
     * 获取右边标题的默认字体颜色
     */
    @Nullable
    ColorStateList getRightTitleColor(@NonNull Context context);

    /**
     * 获取中间标题的默认字体大小
     */
    @Px
    float getTitleSize(@NonNull Context context);

    /**
     * 获取左标题的默认字体大小
     */
    @Px
    float getLeftTitleSize(@NonNull Context context);

    /**
     * 获取右标题的默认字体大小
     */
    @Px
    float getRightTitleSize(@NonNull Context context);

    /**
     * 获取标题的字体样式
     *
     * @param style         文字样式
     *                      常规：{@link Typeface#NORMAL}
     *                      粗体：{@link Typeface#BOLD}
     *                      斜体：{@link Typeface#ITALIC}
     *                      粗斜体：{@link Typeface#BOLD_ITALIC}
     */
    @NonNull
    Typeface getTitleTypeface(@NonNull Context context, int style);

    /**
     * 获取左标题的的字体样式
     *
     * @param style         文字样式
     *                      常规：{@link Typeface#NORMAL}
     *                      粗体：{@link Typeface#BOLD}
     *                      斜体：{@link Typeface#ITALIC}
     *                      粗斜体：{@link Typeface#BOLD_ITALIC}
     */
    @NonNull
    Typeface getLeftTitleTypeface(@NonNull Context context, int style);

    /**
     * 获取右标题的的字体样式
     *
     * @param style         文字样式
     *                      常规：{@link Typeface#NORMAL}
     *                      粗体：{@link Typeface#BOLD}
     *                      斜体：{@link Typeface#ITALIC}
     *                      粗斜体：{@link Typeface#BOLD_ITALIC}
     */
    @NonNull
    Typeface getRightTitleTypeface(@NonNull Context context, int style);

    /**
     * 获取标题的默认样式
     */
    int getTitleStyle(@NonNull Context context);

    /**
     * 获取左标题的默认样式
     */
    int getLeftTitleStyle(@NonNull Context context);

    /**
     * 获取右标题的默认样式
     */
    int getRightTitleStyle(@NonNull Context context);

    /**
     * 获取标题的图标默认重心
     */
    int getTitleIconGravity(@NonNull Context context);

    /**
     * 获取左标题的图标默认重心
     */
    int getLeftIconGravity(@NonNull Context context);

    /**
     * 获取右标题的图标默认重心
     */
    int getRightIconGravity(@NonNull Context context);

    /**
     * 获取标题的图标默认间距
     */
    @Px
    int getTitleIconPadding(@NonNull Context context);

    /**
     * 获取左标题的图标默认间距
     */
    @Px
    int getLeftIconPadding(@NonNull Context context);

    /**
     * 获取右标题的图标默认间距
     */
    @Px
    int getRightIconPadding(@NonNull Context context);

    /**
     * 获取标题的图标默认宽度
     */
    @Px
    int getTitleIconWidth(@NonNull Context context);

    /**
     * 获取左标题的图标默认宽度
     */
    @Px
    int getLeftIconWidth(@NonNull Context context);

    /**
     * 获取右标题的图标默认宽度
     */
    @Px
    int getRightIconWidth(@NonNull Context context);

    /**
     * 获取标题的图标默认高度
     */
    @Px
    int getTitleIconHeight(@NonNull Context context);

    /**
     * 获取左标题的图标默认高度
     */
    @Px
    int getLeftIconHeight(@NonNull Context context);

    /**
     * 获取右标题的图标默认高度
     */
    int getRightIconHeight(@NonNull Context context);

    /**
     * 获取标题文本溢出的处理方式
     */
    @Nullable
    TextUtils.TruncateAt getTitleOverflowMode(@NonNull Context context);

    /**
     * 获取左标题文本溢出的处理方式
     */
    @Nullable
    TextUtils.TruncateAt getLeftTitleOverflowMode(@NonNull Context context);

    /**
     * 获取右标题文本溢出的处理方式
     */
    @Nullable
    TextUtils.TruncateAt getRightTitleOverflowMode(@NonNull Context context);

    /**
     * 分割线是否显示
     */
    boolean isLineVisible(@NonNull Context context);

    /**
     * 获取分割线默认大小
     */
    @Px
    int getLineSize(@NonNull Context context);

    /**
     * 获取分割线默认背景
     */
    @Nullable
    Drawable getLineDrawable(@NonNull Context context);
}