package com.hjq.bar.style;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.widget.TextView;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/TitleBar
 *    time   : 2020/09/19
 *    desc   : 水波纹样式实现（对应布局属性：app:barStyle="ripple"）
 */
public class RippleBarStyle extends TransparentBarStyle {

    @Override
    public TextView createLeftView(Context context) {
        TextView leftView = super.createLeftView(context);
        Drawable drawable = getRippleDrawable(context);
        if (drawable != null) {
            setViewBackground(leftView, drawable);
        }
        return leftView;
    }

    @Override
    public TextView createRightView(Context context) {
        TextView rightView = super.createRightView(context);
        Drawable drawable = getRippleDrawable(context);
        if (drawable != null) {
            setViewBackground(rightView, drawable);
        }
        return rightView;
    }

    /**
     * 获取水波纹的点击效果
     */
    public Drawable getRippleDrawable(Context context) {
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(android.R.attr.selectableItemBackground, typedValue, true)) {
            return getDrawableResources(context, typedValue.resourceId);
        }
        return null;
    }
}