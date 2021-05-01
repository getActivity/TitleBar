package com.hjq.bar.style;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;

import com.hjq.bar.R;
import com.hjq.bar.SelectorDrawable;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/TitleBar
 *    time   : 2020/09/19
 *    desc   : 日间主题样式实现（对应布局属性：app:barStyle="light"）
 */
public class LightBarStyle extends CommonBarStyle {

    @Override
    public TextView createLeftView(Context context) {
        TextView leftView = super.createLeftView(context);
        leftView.setTextColor(0xFF666666);
        setViewBackground(leftView, new SelectorDrawable.Builder()
                .setDefault(new ColorDrawable(0x00000000))
                .setFocused(new ColorDrawable(0x0C000000))
                .setPressed(new ColorDrawable(0x0C000000))
                .build());
        return leftView;
    }

    @Override
    public TextView createTitleView(Context context) {
        TextView titleView = super.createTitleView(context);
        titleView.setTextColor(0xFF222222);
        return titleView;
    }

    @Override
    public TextView createRightView(Context context) {
        TextView rightView = super.createRightView(context);
        rightView.setTextColor(0xFFA4A4A4);
        setViewBackground(rightView, new SelectorDrawable.Builder()
                .setDefault(new ColorDrawable(0x00000000))
                .setFocused(new ColorDrawable(0x0C000000))
                .setPressed(new ColorDrawable(0x0C000000))
                .build());
        return rightView;
    }

    @Override
    public View createLineView(Context context) {
        View lineView = super.createLineView(context);
        setViewBackground(lineView, new ColorDrawable(0xFFECECEC));
        return lineView;
    }

    @Override
    public Drawable createBackgroundDrawable(Context context) {
        return new ColorDrawable(0xFFFFFFFF);
    }

    @Override
    public Drawable createBackIcon(Context context) {
        return getDrawableResources(context, R.drawable.bar_arrows_left_black);
    }
}