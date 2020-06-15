package com.hjq.bar.style;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.TypedValue;
import android.view.Gravity;

import com.hjq.bar.ITitleBarStyle;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/TitleBar
 *    time   : 2018/11/27
 *    desc   : 默认样式基类
 */
public abstract class BaseTitleBarStyle implements ITitleBarStyle {

    private Context mContext;

    public BaseTitleBarStyle(Context context) {
        mContext = context;
    }

    @Override
    public int getTitleGravity() {
        return Gravity.CENTER;
    }

    @Override
    public int getDrawablePadding() {
        return dp2px(2);
    }

    @Override
    public int getChildPadding() {
        return dp2px(12);
    }

    @Override
    public int getLineSize() {
        return 1;
    }

    @Override
    public int getTitleBarHeight() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // 获取 ActionBar 的高度
            TypedArray ta = mContext.obtainStyledAttributes(new int[]{android.R.attr.actionBarSize});
            int actionBarSize = (int) ta.getDimension(0, 0);
            ta.recycle();
            return actionBarSize;
        }
        // ActionBar 的高度为 154 px，计算得出为 56 dp
        return dp2px(56);
    }

    @Override
    public float getLeftSize() {
        return sp2px(14);
    }

    @Override
    public float getTitleSize() {
        return sp2px(16);
    }

    @Override
    public float getRightSize() {
        return sp2px(14);
    }

    public Context getContext() {
        return mContext;
    }

    protected Drawable getDrawable(int id) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return getContext().getResources().getDrawable(id, getContext().getTheme());
        } else {
            return getContext().getResources().getDrawable(id);
        }
    }

    /**
     * dp转px
     */
    protected int dp2px(float dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, getContext().getResources().getDisplayMetrics());
    }

    /**
     * sp转px
     */
    protected int sp2px(float spValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue, getContext().getResources().getDisplayMetrics());
    }
}