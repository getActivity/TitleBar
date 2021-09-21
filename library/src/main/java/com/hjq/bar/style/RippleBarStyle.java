package com.hjq.bar.style;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;

import com.hjq.bar.TitleBarSupport;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/TitleBar
 *    time   : 2020/09/19
 *    desc   : 水波纹样式实现（对应布局属性：app:barStyle="ripple"）
 */
public class RippleBarStyle extends TransparentBarStyle {

    @Override
    public Drawable getLeftTitleBackground(Context context) {
        Drawable drawable = createRippleDrawable(context);
        if (drawable != null) {
            return drawable;
        }
        return super.getLeftTitleBackground(context);
    }

    @Override
    public Drawable getRightTitleBackground(Context context) {
        Drawable drawable = createRippleDrawable(context);
        if (drawable != null) {
            return drawable;
        }
        return super.getRightTitleBackground(context);
    }

    /**
     * 获取水波纹的点击效果
     */
    public Drawable createRippleDrawable(Context context) {
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(android.R.attr.selectableItemBackgroundBorderless, typedValue, true)) {
            return TitleBarSupport.getDrawable(context, typedValue.resourceId);
        }
        return null;
    }
}