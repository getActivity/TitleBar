package com.hjq.bar.style;

import android.content.Context;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.hjq.bar.ITitleBarStyle;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/TitleBar
 *    time   : 2020/09/19
 *    desc   : 默认初始化器基类
 */
public abstract class CommonBarStyle implements ITitleBarStyle {

    @Override
    public TextView createLeftView(Context context) {
        TextView leftView = createTextView(context);
        leftView.setGravity(Gravity.CENTER_VERTICAL);
        leftView.setFocusable(true);
        leftView.setSingleLine();
        leftView.setEllipsize(TextUtils.TruncateAt.END);
        return leftView;
    }

    @Override
    public TextView createTitleView(Context context) {
        TextView titleView = createTextView(context);
        titleView.setGravity(Gravity.CENTER);
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

    @Override
    public TextView createRightView(Context context) {
        TextView rightView = createTextView(context);
        rightView.setGravity(Gravity.CENTER_VERTICAL);
        rightView.setFocusable(true);
        rightView.setSingleLine();
        rightView.setEllipsize(TextUtils.TruncateAt.END);
        return rightView;
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
    public int getLeftTitleSize(Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 14, context.getResources().getDisplayMetrics());
    }

    @Override
    public int getTitleTitleSize(Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, context.getResources().getDisplayMetrics());
    }

    @Override
    public int getRightTitleSize(Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 14, context.getResources().getDisplayMetrics());
    }

    @Override
    public int getLeftIconGravity(Context context) {
        return Gravity.START;
    }

    @Override
    public int getTitleIconGravity(Context context) {
        return Gravity.END;
    }

    @Override
    public int getRightIconGravity(Context context) {
        return Gravity.END;
    }

    @Override
    public int getLeftIconPadding(Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, context.getResources().getDisplayMetrics());
    }

    @Override
    public int getTitleIconPadding(Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, context.getResources().getDisplayMetrics());
    }

    @Override
    public int getRightIconPadding(Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, context.getResources().getDisplayMetrics());
    }

    @Override
    public int getLeftIconWidth(Context context) {
        return 0;
    }

    @Override
    public int getTitleIconWidth(Context context) {
        return 0;
    }

    @Override
    public int getRightIconWidth(Context context) {
        return 0;
    }

    @Override
    public int getLeftIconHeight(Context context) {
        return 0;
    }

    @Override
    public int getTitleIconHeight(Context context) {
        return 0;
    }

    @Override
    public int getRightIconHeight(Context context) {
        return 0;
    }

    @Override
    public int getLineSize(Context context) {
        return 1;
    }

    /**
     * 创建 TextView
     */
    protected TextView createTextView(Context context) {
        return new TextView(context);
    }
}