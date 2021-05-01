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
 *    desc   : 透明主题样式实现（对应布局属性：app:barStyle="transparent"）
 */
public class TransparentBarStyle extends CommonBarStyle {

    @Override
    public TextView createLeftView(Context context) {
        TextView leftView = super.createLeftView(context);
        leftView.setTextColor(0xFFFFFFFF);
        setViewBackground(leftView, new SelectorDrawable.Builder()
                .setDefault(new ColorDrawable(0x00000000))
                .setFocused(new ColorDrawable(0x22000000))
                .setPressed(new ColorDrawable(0x22000000))
                .build());
        return leftView;
    }

    @Override
    public TextView createTitleView(Context context) {
        TextView titleView = super.createTitleView(context);
        titleView.setTextColor(0xFFFFFFFF);
        return titleView;
    }

    @Override
    public TextView createRightView(Context context) {
        TextView rightView = super.createRightView(context);
        rightView.setTextColor(0xFFFFFFFF);
        setViewBackground(rightView, new SelectorDrawable.Builder()
                .setDefault(new ColorDrawable(0x00000000))
                .setFocused(new ColorDrawable(0x22000000))
                .setPressed(new ColorDrawable(0x22000000))
                .build());
        return rightView;
    }

    @Override
    public View createLineView(Context context) {
        View lineView = super.createLineView(context);
        setViewBackground(lineView, new ColorDrawable(0xFFECECEC));
        lineView.setVisibility(View.INVISIBLE);
        return lineView;
    }

    @Override
    public Drawable createBackIcon(Context context) {
        return getDrawableResources(context, R.drawable.bar_arrows_left_white);
    }

    @Override
    public Drawable createBackgroundDrawable(Context context) {
        return new ColorDrawable(0x00000000);
    }
}