package com.hjq.bar.style;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.hjq.bar.R;
import com.hjq.bar.SelectorDrawable;
import com.hjq.bar.TitleBarSupport;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/TitleBar
 *    time   : 2020/09/19
 *    desc   : 日间主题样式实现（对应布局属性：app:barStyle="light"）
 */
public class LightBarStyle extends CommonBarStyle {

    @Nullable
    @Override
    public Drawable getTitleBarBackground(@NonNull Context context) {
        return new ColorDrawable(0xFFFFFFFF);
    }

    @Nullable
    @Override
    public Drawable getLeftTitleBackground(@NonNull Context context) {
        return new SelectorDrawable.Builder()
                .setDefault(new ColorDrawable(0x00000000))
                .setFocused(new ColorDrawable(0x0C000000))
                .setPressed(new ColorDrawable(0x0C000000))
                .build();
    }

    @Nullable
    @Override
    public Drawable getRightTitleBackground(@NonNull Context context) {
        return new SelectorDrawable.Builder()
                .setDefault(new ColorDrawable(0x00000000))
                .setFocused(new ColorDrawable(0x0C000000))
                .setPressed(new ColorDrawable(0x0C000000))
                .build();
    }

    @Nullable
    @Override
    public Drawable getBackButtonDrawable(@NonNull Context context) {
        return TitleBarSupport.getDrawable(context, R.drawable.bar_arrows_left_black);
    }

    @Nullable
    @Override
    public ColorStateList getTitleColor(@NonNull Context context) {
        return ColorStateList.valueOf(0xFF222222);
    }

    @Nullable
    @Override
    public ColorStateList getLeftTitleColor(@NonNull Context context) {
        return ColorStateList.valueOf(0xFF666666);
    }

    @Nullable
    @Override
    public ColorStateList getRightTitleColor(@NonNull Context context) {
        return ColorStateList.valueOf(0xFFA4A4A4);
    }

    @Nullable
    @Override
    public Drawable getLineDrawable(@NonNull Context context) {
        return new ColorDrawable(0xFFECECEC);
    }
}