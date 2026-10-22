package com.hjq.bar.style;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import com.hjq.bar.ITitleBarStyle;
import com.hjq.bar.TitleBarSupport;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/TitleBar
 *    time   : 2020/09/19
 *    desc   : 默认初始化器基类
 */
public abstract class CommonBarStyle implements ITitleBarStyle {

    @NonNull
    @Override
    public TextView createTitleView(@NonNull Context context) {
        TextView titleView = newTitleView(context);
        titleView.setGravity(Gravity.CENTER_VERTICAL);
        titleView.setFocusable(true);
        titleView.setSingleLine();
        return titleView;
    }

    @NonNull
    public TextView newTitleView(@NonNull Context context) {
        return new TextView(context);
    }

    @NonNull
    @Override
    public TextView createLeftView(@NonNull Context context) {
        TextView leftView = newLeftView(context);
        leftView.setGravity(Gravity.CENTER_VERTICAL);
        leftView.setFocusable(true);
        leftView.setSingleLine();
        return leftView;
    }

    @NonNull
    public TextView newLeftView(@NonNull Context context) {
        return new TextView(context);
    }

    @NonNull
    @Override
    public TextView createRightView(@NonNull Context context) {
        TextView rightView = newRightView(context);
        rightView.setGravity(Gravity.CENTER_VERTICAL);
        rightView.setFocusable(true);
        rightView.setSingleLine();
        return rightView;
    }

    @NonNull
    public TextView newRightView(@NonNull Context context) {
        return new TextView(context);
    }

    @NonNull
    @Override
    public View createLineView(@NonNull Context context) {
        return new View(context);
    }

    @Override
    public Drawable getLeftTitleForeground(@NonNull Context context) {
        return null;
    }

    @Override
    public Drawable getRightTitleForeground(@NonNull Context context) {
        return null;
    }

    @Px
    @Override
    public int getLeftHorizontalPadding(@NonNull Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, context.getResources().getDisplayMetrics());
    }

    @Px
    @Override
    public int getTitleHorizontalPadding(@NonNull Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0, context.getResources().getDisplayMetrics());
    }

    @Px
    @Override
    public int getRightHorizontalPadding(@NonNull Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, context.getResources().getDisplayMetrics());
    }

    @Px
    @Override
    public int getChildVerticalPadding(@NonNull Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 15, context.getResources().getDisplayMetrics());
    }

    @Nullable
    @Override
    public CharSequence getTitle(@NonNull Context context) {
        return null;
    }

    @Nullable
    @Override
    public CharSequence getLeftTitle(@NonNull Context context) {
        return null;
    }

    @Nullable
    @Override
    public CharSequence getRightTitle(@NonNull Context context) {
        return null;
    }

    @Px
    @Override
    public float getTitleSize(@NonNull Context context) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, context.getResources().getDisplayMetrics());
    }

    @Px
    @Override
    public float getLeftTitleSize(@NonNull Context context) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 14, context.getResources().getDisplayMetrics());
    }

    @Px
    @Override
    public float getRightTitleSize(@NonNull Context context) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 14, context.getResources().getDisplayMetrics());
    }

    @NonNull
    @Override
    public Typeface getTitleTypeface(@NonNull Context context, int style) {
        return TitleBarSupport.getTextTypeface(style);
    }

    @NonNull
    @Override
    public Typeface getLeftTitleTypeface(@NonNull Context context, int style) {
        return TitleBarSupport.getTextTypeface(style);
    }

    @NonNull
    @Override
    public Typeface getRightTitleTypeface(@NonNull Context context, int style) {
        return TitleBarSupport.getTextTypeface(style);
    }

    @Override
    public int getTitleStyle(@NonNull Context context) {
        return Typeface.NORMAL;
    }

    @Override
    public int getLeftTitleStyle(@NonNull Context context) {
        return Typeface.NORMAL;
    }

    @Override
    public int getRightTitleStyle(@NonNull Context context) {
        return Typeface.NORMAL;
    }

    @Override
    public int getTitleIconGravity(@NonNull Context context) {
        return Gravity.END;
    }

    @Override
    public int getLeftIconGravity(@NonNull Context context) {
        return Gravity.START;
    }

    @Override
    public int getRightIconGravity(@NonNull Context context) {
        return Gravity.END;
    }

    @Px
    @Override
    public int getTitleIconPadding(@NonNull Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, context.getResources().getDisplayMetrics());
    }

    @Px
    @Override
    public int getLeftIconPadding(@NonNull Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, context.getResources().getDisplayMetrics());
    }

    @Px
    @Override
    public int getRightIconPadding(@NonNull Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, context.getResources().getDisplayMetrics());
    }

    @Px
    @Override
    public int getTitleIconWidth(@NonNull Context context) {
        return 0;
    }

    @Px
    @Override
    public int getLeftIconWidth(@NonNull Context context) {
        return 0;
    }

    @Px
    @Override
    public int getRightIconWidth(@NonNull Context context) {
        return 0;
    }

    @Px
    @Override
    public int getTitleIconHeight(@NonNull Context context) {
        return 0;
    }

    @Px
    @Override
    public int getLeftIconHeight(@NonNull Context context) {
        return 0;
    }

    @Px
    @Override
    public int getRightIconHeight(@NonNull Context context) {
        return 0;
    }

    @Nullable
    @Override
    public TextUtils.TruncateAt getTitleOverflowMode(@NonNull Context context) {
        return TextUtils.TruncateAt.MARQUEE;
    }

    @Nullable
    @Override
    public TextUtils.TruncateAt getLeftTitleOverflowMode(@NonNull Context context) {
        return null;
    }

    @Nullable
    @Override
    public TextUtils.TruncateAt getRightTitleOverflowMode(@NonNull Context context) {
        return null;
    }

    @Override
    public boolean isLineVisible(@NonNull Context context) {
        return true;
    }

    @Px
    @Override
    public int getLineSize(@NonNull Context context) {
        return 1;
    }
}