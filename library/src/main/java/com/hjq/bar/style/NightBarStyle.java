package com.hjq.bar.style;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;

import com.hjq.bar.R;
import com.hjq.bar.SelectorDrawable;
import com.hjq.bar.TitleBarSupport;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/TitleBar
 *    time   : 2020/09/19
 *    desc   : 夜间主题样式实现（对应布局属性：app:barStyle="night"）
 */
public class NightBarStyle extends CommonBarStyle {

    @Override
    public Drawable getBackButtonDrawable(Context context) {
        return TitleBarSupport.getDrawable(context, R.drawable.bar_arrows_left_white);
    }

    @Override
    public Drawable getLeftTitleBackground(Context context) {
        return new SelectorDrawable.Builder()
                .setDefault(new ColorDrawable(0x00000000))
                .setFocused(new ColorDrawable(0x66FFFFFF))
                .setPressed(new ColorDrawable(0x66FFFFFF))
                .build();
    }

    @Override
    public Drawable getRightTitleBackground(Context context) {
        return new SelectorDrawable.Builder()
                .setDefault(new ColorDrawable(0x00000000))
                .setFocused(new ColorDrawable(0x66FFFFFF))
                .setPressed(new ColorDrawable(0x66FFFFFF))
                .build();
    }

    @Override
    public Drawable getTitleBarBackground(Context context) {
        return new ColorDrawable(0xFF000000);
    }

    @Override
    public ColorStateList getTitleColor(Context context) {
        return ColorStateList.valueOf(0xEEFFFFFF);
    }

    @Override
    public ColorStateList getLeftTitleColor(Context context) {
        return ColorStateList.valueOf(0xCCFFFFFF);
    }

    @Override
    public ColorStateList getRightTitleColor(Context context) {
        return ColorStateList.valueOf(0xCCFFFFFF);
    }

    @Override
    public Drawable getLineDrawable(Context context) {
        return new ColorDrawable(0xFFFFFFFF);
    }
}