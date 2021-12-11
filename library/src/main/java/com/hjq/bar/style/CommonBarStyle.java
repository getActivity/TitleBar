package com.hjq.bar.style;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.hjq.bar.ITitleBarStyle;
import com.hjq.bar.TitleBarSupport;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/TitleBar
 *    time   : 2020/09/19
 *    desc   : 默认初始化器基类
 */
public abstract class CommonBarStyle implements ITitleBarStyle {

    @Override
    public TextView createTitleView(Context context) {
        TextView titleView = newTitleView(context);
        titleView.setGravity(Gravity.CENTER_VERTICAL);
        titleView.setFocusable(true);
        titleView.setSingleLine();
        // 给标题设置跑马灯效果（仅在标题过长的时候才会显示）
        titleView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        // 设置跑马灯的循环次数
        titleView.setMarqueeRepeatLimit(-1);
        // 设置跑马灯之后需要设置选中才能有效果
        titleView.setSelected(true);
        return titleView;
    }

    public TextView newTitleView(Context context) {
        return new TextView(context);
    }

    @Override
    public TextView createLeftView(Context context) {
        TextView leftView = newLeftView(context);
        leftView.setGravity(Gravity.CENTER_VERTICAL);
        leftView.setFocusable(true);
        leftView.setSingleLine();
        leftView.setEllipsize(TextUtils.TruncateAt.END);
        return leftView;
    }

    public TextView newLeftView(Context context) {
        return new TextView(context);
    }

    @Override
    public TextView createRightView(Context context) {
        TextView rightView = newRightView(context);
        rightView.setGravity(Gravity.CENTER_VERTICAL);
        rightView.setFocusable(true);
        rightView.setSingleLine();
        rightView.setEllipsize(TextUtils.TruncateAt.END);
        return rightView;
    }

    public TextView newRightView(Context context) {
        return new TextView(context);
    }

    @Override
    public View createLineView(Context context) {
        return new View(context);
    }

    @Override
    public int getChildHorizontalPadding(Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 12, context.getResources().getDisplayMetrics());
    }

    @Override
    public int getChildVerticalPadding(Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 15, context.getResources().getDisplayMetrics());
    }

    @Override
    public CharSequence getTitle(Context context) {
        // 如果当前上下文对象是 Activity，就获取 Activity 的 label 属性作为标题栏的标题
        if (context instanceof Activity) {
            // 获取清单文件中的 android:label 属性值
            CharSequence label = ((Activity) context).getTitle();
            if (!TextUtils.isEmpty(label)) {
                try {
                    PackageManager packageManager = context.getPackageManager();
                    PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
                    // 如果当前 Activity 没有设置 android:label 属性，则默认会返回 App 名称，则需要过滤掉
                    if (!label.toString().equals(packageInfo.applicationInfo.loadLabel(packageManager).toString())) {
                        // 设置标题
                        return label;
                    }
                } catch (PackageManager.NameNotFoundException ignored) {}
            }
        }
        return "";
    }

    @Override
    public CharSequence getLeftTitle(Context context) {
        return "";
    }

    @Override
    public CharSequence getRightTitle(Context context) {
        return "";
    }

    @Override
    public float getTitleSize(Context context) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, context.getResources().getDisplayMetrics());
    }

    @Override
    public float getLeftTitleSize(Context context) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 14, context.getResources().getDisplayMetrics());
    }

    @Override
    public float getRightTitleSize(Context context) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 14, context.getResources().getDisplayMetrics());
    }

    @Override
    public Typeface getTitleTypeface(Context context, int style) {
        return TitleBarSupport.getTextTypeface(style);
    }

    @Override
    public Typeface getLeftTitleTypeface(Context context, int style) {
        return TitleBarSupport.getTextTypeface(style);
    }

    @Override
    public Typeface getRightTitleTypeface(Context context, int style) {
        return TitleBarSupport.getTextTypeface(style);
    }

    @Override
    public int getTitleStyle(Context context) {
        return Typeface.NORMAL;
    }

    @Override
    public int getLeftTitleStyle(Context context) {
        return Typeface.NORMAL;
    }

    @Override
    public int getRightTitleStyle(Context context) {
        return Typeface.NORMAL;
    }

    @Override
    public int getTitleIconGravity(Context context) {
        return Gravity.END;
    }

    @Override
    public int getLeftIconGravity(Context context) {
        return Gravity.START;
    }

    @Override
    public int getRightIconGravity(Context context) {
        return Gravity.END;
    }

    @Override
    public int getTitleIconPadding(Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, context.getResources().getDisplayMetrics());
    }

    @Override
    public int getLeftIconPadding(Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, context.getResources().getDisplayMetrics());
    }

    @Override
    public int getRightIconPadding(Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, context.getResources().getDisplayMetrics());
    }

    @Override
    public int getTitleIconWidth(Context context) {
        return 0;
    }

    @Override
    public int getLeftIconWidth(Context context) {
        return 0;
    }

    @Override
    public int getRightIconWidth(Context context) {
        return 0;
    }

    @Override
    public int getTitleIconHeight(Context context) {
        return 0;
    }

    @Override
    public int getLeftIconHeight(Context context) {
        return 0;
    }

    @Override
    public int getRightIconHeight(Context context) {
        return 0;
    }

    @Override
    public boolean isLineVisible(Context context) {
        return true;
    }

    @Override
    public int getLineSize(Context context) {
        return 1;
    }
}