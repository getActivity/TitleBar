package com.hjq.bar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.hjq.bar.initializer.BaseBarInitializer;
import com.hjq.bar.initializer.LightBarInitializer;
import com.hjq.bar.initializer.NightBarInitializer;
import com.hjq.bar.initializer.RippleBarInitializer;
import com.hjq.bar.initializer.TransparentBarInitializer;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/TitleBar
 *    time   : 2018/08/17
 *    desc   : Android 通用标题栏
 */
public class TitleBar extends FrameLayout
        implements View.OnClickListener,
        View.OnLayoutChangeListener {

    /** 默认初始化器 */
    private static ITitleBarInitializer sGlobalInitializer;
    /** 当前初始化器 */
    private final ITitleBarInitializer mCurrentInitializer;

    /** 监听器对象 */
    private OnTitleBarListener mListener;

    /** 标题栏子控件 */
    private final TextView mLeftView, mTitleView, mRightView;
    private final View mLineView;

    /** 控件内间距 */
    private int mHorizontalPadding, mVerticalPadding;

    /** 图标显示大小 */
    private int mDrawableSize = -1;

    public TitleBar(Context context) {
        this(context, null, 0);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        if (sGlobalInitializer == null) {
            sGlobalInitializer = new LightBarInitializer();
        }

        final TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.TitleBar);

        // 标题栏样式设置
        switch (array.getInt(R.styleable.TitleBar_barStyle, 0)) {
            case 0x10:
                mCurrentInitializer = new LightBarInitializer();
                break;
            case 0x20:
                mCurrentInitializer = new NightBarInitializer();
                break;
            case 0x30:
                mCurrentInitializer = new TransparentBarInitializer();
                break;
            case 0x40:
                mCurrentInitializer = new RippleBarInitializer();
                break;
            default:
                mCurrentInitializer = TitleBar.sGlobalInitializer;
                break;
        }

        mLeftView = mCurrentInitializer.getLeftView(context);
        mTitleView = mCurrentInitializer.getTitleView(context);
        mRightView = mCurrentInitializer.getRightView(context);
        mLineView = mCurrentInitializer.getLineView(context);

        // 限制图标显示的大小
        if (array.hasValue(R.styleable.TitleBar_drawableSize)) {
            setDrawableSize(array.getDimensionPixelSize(R.styleable.TitleBar_drawableSize, 0));
        }

        // 设置文字和图标之间的间距
        if (array.hasValue(R.styleable.TitleBar_android_drawablePadding)) {
            setDrawablePadding(array.getDimensionPixelSize(R.styleable.TitleBar_android_drawablePadding, 0));
        }

        // 标题设置
        if (array.hasValue(R.styleable.TitleBar_leftTitle)) {
            setLeftTitle(array.getString(R.styleable.TitleBar_leftTitle));
        }

        if (array.hasValue(R.styleable.TitleBar_title)) {
            setTitle(array.getString(R.styleable.TitleBar_title));
        } else {
            // 如果当前上下文对象是 Activity，就获取 Activity 的 label 属性作为标题栏的标题
            if (context instanceof Activity) {
                // 获取清单文件中的 android:label 属性值
                CharSequence label = ((Activity) context).getTitle();
                if (!TextUtils.isEmpty(label)) {
                    try {
                        PackageManager packageManager = context.getPackageManager();
                        PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
                        // 如果当前 Activity 没有设置 android:label 属性，则默认会返回 APP 名称，则需要过滤掉
                        if (!label.toString().equals(packageInfo.applicationInfo.loadLabel(packageManager).toString())) {
                            // 设置标题
                            setTitle(label);
                        }
                    } catch (PackageManager.NameNotFoundException ignored) {}
                }
            }
        }

        if (array.hasValue(R.styleable.TitleBar_rightTitle)) {
            setRightTitle(array.getString(R.styleable.TitleBar_rightTitle));
        }

        // 图标设置
        if (array.hasValue(R.styleable.TitleBar_leftIcon)) {
            setLeftIcon(BaseBarInitializer.getDrawableResources(getContext(), array.getResourceId(R.styleable.TitleBar_leftIcon, 0)));
        } else {
            if (!array.getBoolean(R.styleable.TitleBar_backButton, true)) {
                // 不显示返回图标
                setLeftIcon(null);
            }
        }

        if (array.hasValue(R.styleable.TitleBar_rightIcon)) {
            setRightIcon(BaseBarInitializer.getDrawableResources(getContext(), array.getResourceId(R.styleable.TitleBar_rightIcon, 0)));
        }

        // 图标颜色设置
        if (array.hasValue(R.styleable.TitleBar_leftTint)) {
            setLeftTint(array.getColor(R.styleable.TitleBar_leftTint, 0));
        }

        if (array.hasValue(R.styleable.TitleBar_rightTint)) {
            setRightTint(array.getColor(R.styleable.TitleBar_rightTint, 0));
        }

        // 文字颜色设置
        if (array.hasValue(R.styleable.TitleBar_leftColor)) {
            setLeftColor(array.getColor(R.styleable.TitleBar_leftColor, 0));
        }

        if (array.hasValue(R.styleable.TitleBar_titleColor)) {
            setTitleColor(array.getColor(R.styleable.TitleBar_titleColor, 0));
        }

        if (array.hasValue(R.styleable.TitleBar_rightColor)) {
            setRightColor(array.getColor(R.styleable.TitleBar_rightColor, 0));
        }

        // 文字大小设置
        if (array.hasValue(R.styleable.TitleBar_leftSize)) {
            setLeftSize(TypedValue.COMPLEX_UNIT_PX, array.getDimensionPixelSize(R.styleable.TitleBar_leftSize, 0));
        }

        if (array.hasValue(R.styleable.TitleBar_titleSize)) {
            setTitleSize(TypedValue.COMPLEX_UNIT_PX, array.getDimensionPixelSize(R.styleable.TitleBar_titleSize, 0));
        }

        if (array.hasValue(R.styleable.TitleBar_rightSize)) {
            setRightSize(TypedValue.COMPLEX_UNIT_PX, array.getDimensionPixelSize(R.styleable.TitleBar_rightSize, 0));
        }

        // 背景设置
        if (array.hasValue(R.styleable.TitleBar_leftBackground)) {
            setLeftBackground(array.getDrawable(R.styleable.TitleBar_leftBackground));
        }

        if (array.hasValue(R.styleable.TitleBar_rightBackground)) {
            setRightBackground(array.getDrawable(R.styleable.TitleBar_rightBackground));
        }

        // 分割线设置
        if (array.hasValue(R.styleable.TitleBar_lineColor)) {
            setLineDrawable(array.getDrawable(R.styleable.TitleBar_lineColor));
        }

        if (array.hasValue(R.styleable.TitleBar_titleGravity)) {
            setTitleGravity(array.getInt(R.styleable.TitleBar_titleGravity, Gravity.NO_GRAVITY));
        }

        if (array.hasValue(R.styleable.TitleBar_titleStyle)) {
            setTitleStyle(Typeface.defaultFromStyle(array.getInt(R.styleable.TitleBar_titleStyle, Typeface.NORMAL)));
        }

        if (array.hasValue(R.styleable.TitleBar_lineVisible)) {
            setLineVisible(array.getBoolean(R.styleable.TitleBar_lineVisible, false));
        }

        if (array.hasValue(R.styleable.TitleBar_lineSize)) {
            setLineSize(array.getDimensionPixelSize(R.styleable.TitleBar_lineSize, 0));
        }

        // 如果设置了这两个属性，则将内间距置为空
        if (array.hasValue(R.styleable.TitleBar_android_paddingHorizontal) || array.hasValue(R.styleable.TitleBar_android_paddingVertical)) {
            setPadding(0, 0, 0, 0);
        }
        // 获取子 View 水平内间距
        mHorizontalPadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, array.getDimensionPixelSize(R.styleable.TitleBar_android_paddingHorizontal,
                mCurrentInitializer.getHorizontalPadding(getContext())), getResources().getDisplayMetrics());
        // 获取子 View 垂直内间距
        mVerticalPadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, array.getDimensionPixelSize(R.styleable.TitleBar_android_paddingVertical,
                mCurrentInitializer.getVerticalPadding(getContext())), getResources().getDisplayMetrics());

        // 回收 TypedArray 对象
        array.recycle();

        // 设置默认背景
        if (getBackground() == null) {
            BaseBarInitializer.setViewBackground(this, mCurrentInitializer.getBackgroundDrawable(context));
        }

        addView(mTitleView, 0);
        addView(mLeftView, 1);
        addView(mRightView, 2);
        addView(mLineView, 3);

        addOnLayoutChangeListener(this);
    }

    /**
     * {@link View.OnLayoutChangeListener}
     */

    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        // 先移除当前的监听，因为 TextView.setMaxWidth 方法会重新触发监听
        removeOnLayoutChangeListener(this);

        if (mLeftView.getMaxWidth() != Integer.MAX_VALUE &&
                mTitleView.getMaxWidth() != Integer.MAX_VALUE &&
                mRightView.getMaxWidth() != Integer.MAX_VALUE) {
            // 不限制子 View 的宽度
            mLeftView.setMaxWidth(Integer.MAX_VALUE);
            mTitleView.setMaxWidth(Integer.MAX_VALUE);
            mRightView.setMaxWidth(Integer.MAX_VALUE);
            // 对子 View 重新进行测量
            mLeftView.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED), MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            mTitleView.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED), MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            mRightView.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED), MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
        }

        // 标题栏子 View 最大宽度限制算法
        int barWidth = right - left;
        int sideWidth = Math.max(mLeftView.getMeasuredWidth(), mRightView.getMeasuredWidth());
        int maxWidth = sideWidth * 2 + mTitleView.getMeasuredWidth();
        // 算出来子 View 的宽大于标题栏的宽度
        if (maxWidth >= barWidth) {
            // 判断是左右项太长还是标题项太长
            if (sideWidth > barWidth / 3) {
                // 如果是左右项太长，那么按照比例进行划分
                mLeftView.setMaxWidth(barWidth / 4);
                mTitleView.setMaxWidth(barWidth / 2);
                mRightView.setMaxWidth(barWidth / 4);
            } else {
                // 如果是标题项太长，那么就进行动态计算
                mLeftView.setMaxWidth(sideWidth);
                mTitleView.setMaxWidth(barWidth - sideWidth * 2);
                mRightView.setMaxWidth(sideWidth);
            }
        } else {
            if (mLeftView.getMaxWidth() != Integer.MAX_VALUE &&
                    mTitleView.getMaxWidth() != Integer.MAX_VALUE &&
                    mRightView.getMaxWidth() != Integer.MAX_VALUE) {
                // 不限制子 View 的最大宽度
                mLeftView.setMaxWidth(Integer.MAX_VALUE);
                mTitleView.setMaxWidth(Integer.MAX_VALUE);
                mRightView.setMaxWidth(Integer.MAX_VALUE);
            }
        }

        // TextView 里面必须有东西才能被点击
        mLeftView.setEnabled(BaseBarInitializer.checkContainContent(mLeftView));
        mTitleView.setEnabled(BaseBarInitializer.checkContainContent(mTitleView));
        mRightView.setEnabled(BaseBarInitializer.checkContainContent(mRightView));

        post(new Runnable() {
            @Override
            public void run() {
                // 这里再次监听需要延迟，否则会导致递归的情况发生
                addOnLayoutChangeListener(TitleBar.this);
            }
        });
    }

    /**
     * {@link View.OnClickListener}
     */

    @Override
    public void onClick(View v) {
        if (mListener == null) {
            return;
        }

        if (v == mLeftView) {
            mListener.onLeftClick(v);
        } else if (v == mRightView) {
            mListener.onRightClick(v);
        } else if (v == mTitleView) {
            mListener.onTitleClick(v);
        }
    }

    @Override
    public void setLayoutParams(ViewGroup.LayoutParams params) {
        if (params.width == LayoutParams.WRAP_CONTENT) {
            // 如果当前宽度是自适应则转换成占满父布局
            params.width = LayoutParams.MATCH_PARENT;
        }

        int horizontalPadding = mHorizontalPadding;
        int verticalPadding = 0;
        // 如果当前高度是自适应则设置默认的内间距
        if (params.height == ViewGroup.LayoutParams.WRAP_CONTENT) {
            verticalPadding = mVerticalPadding;
        }

        setChildPadding(horizontalPadding, verticalPadding);
        super.setLayoutParams(params);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
    }

    /**
     * 设置监听器
     */
    public TitleBar setOnTitleBarListener(OnTitleBarListener l) {
        mListener = l;
        // 设置监听
        mTitleView.setOnClickListener(this);
        mLeftView.setOnClickListener(this);
        mRightView.setOnClickListener(this);
        return this;
    }

    /**
     * 设置标题
     */
    public TitleBar setTitle(int id) {
        return setTitle(getResources().getString(id));
    }

    public TitleBar setTitle(CharSequence text) {
        mTitleView.setText(text);
        return this;
    }

    /**
     * 获取标题
     */
    public CharSequence getTitle() {
        return mTitleView.getText();
    }

    /**
     * 设置左标题
     */
    public TitleBar setLeftTitle(int id) {
        return setLeftTitle(getResources().getString(id));
    }

    public TitleBar setLeftTitle(CharSequence text) {
        mLeftView.setText(text);
        return this;
    }

    /**
     * 获取左标题
     */
    public CharSequence getLeftTitle() {
        return mLeftView.getText();
    }

    /**
     * 设置右标题
     */
    public TitleBar setRightTitle(int id) {
        return setRightTitle(getResources().getString(id));
    }

    public TitleBar setRightTitle(CharSequence text) {
        mRightView.setText(text);
        return this;
    }

    /**
     * 获取右标题
     */
    public CharSequence getRightTitle() {
        return mRightView.getText();
    }

    /**
     * 设置左图标
     */
    public TitleBar setLeftIcon(int id) {
        return setLeftIcon(BaseBarInitializer.getDrawableResources(getContext(), id));
    }

    public TitleBar setLeftIcon(Drawable drawable) {
        if (drawable != null) {
            if (mDrawableSize != -1) {
                drawable.setBounds(0, 0, mDrawableSize, mDrawableSize);
            } else {
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            }
        }
        mLeftView.setCompoundDrawables(drawable, null, null, null);
        return this;
    }

    /**
     * 设置左图标色彩
     */
    public TitleBar setLeftTint(int color) {
        Drawable drawable = getLeftIcon();
        if (drawable != null) {
            drawable.mutate();
            drawable.setColorFilter(color, PorterDuff.Mode.SRC_IN);
        }
        return this;
    }

    /**
     * 获取左图标
     */
    public Drawable getLeftIcon() {
        return mLeftView.getCompoundDrawables()[0];
    }

    /**
     * 设置右图标
     */
    public TitleBar setRightIcon(int id) {
        return setRightIcon(BaseBarInitializer.getDrawableResources(getContext(), id));
    }

    public TitleBar setRightIcon(Drawable drawable) {
        if (drawable != null) {
            if (mDrawableSize != -1) {
                drawable.setBounds(0, 0, mDrawableSize, mDrawableSize);
            } else {
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            }
        }
        mRightView.setCompoundDrawables(null, null, drawable, null);
        return this;
    }

    /**
     * 设置右图标色彩
     */
    public TitleBar setRightTint(int color) {
        Drawable drawable = getRightIcon();
        if (drawable != null) {
            drawable.mutate();
            drawable.setColorFilter(color, PorterDuff.Mode.SRC_IN);
        }
        return this;
    }

    /**
     * 获取右图标
     */
    public Drawable getRightIcon() {
        return mRightView.getCompoundDrawables()[2];
    }

    /**
     * 设置标题颜色
     */
    public TitleBar setTitleColor(int color) {
        mTitleView.setTextColor(color);
        return this;
    }

    /**
     * 设置左标题颜色
     */
    public TitleBar setLeftColor(int color) {
        mLeftView.setTextColor(color);
        return this;
    }

    /**
     * 设置右标题颜色
     */
    public TitleBar setRightColor(int color) {
        mRightView.setTextColor(color);
        return this;
    }

    /**
     * 设置左标题状态选择器
     */
    public TitleBar setLeftBackground(int id) {
        return setLeftBackground(BaseBarInitializer.getDrawableResources(getContext(), id));
    }

    public TitleBar setLeftBackground(Drawable drawable) {
        BaseBarInitializer.setViewBackground(mLeftView, drawable);
        return this;
    }

    /**
     * 设置右标题状态选择器
     */
    public TitleBar setRightBackground(int id) {
        return setRightBackground(BaseBarInitializer.getDrawableResources(getContext(), id));
    }

    public TitleBar setRightBackground(Drawable drawable) {
        BaseBarInitializer.setViewBackground(mRightView, drawable);
        return this;
    }

    /**
     * 设置标题文字大小
     */
    public TitleBar setTitleSize(int unit, float size) {
        mTitleView.setTextSize(unit, size);
        return this;
    }

    /**
     * 设置左标题文字大小
     */
    public TitleBar setLeftSize(int unit, float size) {
        mLeftView.setTextSize(unit, size);
        return this;
    }

    /**
     * 设置右标题文字大小
     */
    public TitleBar setRightSize(int unit, float size) {
        mRightView.setTextSize(unit, size);
        return this;
    }

    /**
     * 设置分割线是否显示
     */
    public TitleBar setLineVisible(boolean visible) {
        mLineView.setVisibility(visible ? VISIBLE : INVISIBLE);
        return this;
    }

    /**
     * 设置分割线的颜色
     */
    public TitleBar setLineColor(int color) {
        return setLineDrawable(new ColorDrawable(color));
    }

    public TitleBar setLineDrawable(Drawable drawable) {
        BaseBarInitializer.setViewBackground(mLineView, drawable);
        return this;
    }

    /**
     * 设置分割线的大小
     */
    public TitleBar setLineSize(int px) {
        ViewGroup.LayoutParams layoutParams = mLineView.getLayoutParams();
        layoutParams.height = px;
        mLineView.setLayoutParams(layoutParams);
        return this;
    }

    /**
     * 设置标题重心
     */
    @SuppressLint("RtlHardcoded")
    public TitleBar setTitleGravity(int gravity) {
        // 适配布局反方向
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            gravity = Gravity.getAbsoluteGravity(gravity, getResources().getConfiguration().getLayoutDirection());
        }
        // 如果标题的重心为左，那么左边就不能有内容
        // 如果标题的重心为右，那么右边就不能有内容
        if (((gravity & Gravity.LEFT) != 0 && BaseBarInitializer.checkContainContent(mLeftView)) ||
                ((gravity & Gravity.RIGHT) != 0) && BaseBarInitializer.checkContainContent(mRightView)) {
            throw new IllegalArgumentException("are you ok?");
        }
        LayoutParams params = (LayoutParams) mTitleView.getLayoutParams();
        params.gravity = gravity;
        mTitleView.setLayoutParams(params);
        return this;
    }

    /**
     * 设置标题文字样式
     */
    public TitleBar setTitleStyle(Typeface typeface) {
        mTitleView.setTypeface(typeface);
        return this;
    }

    /**
     * 设置图标显示大小
     */
    public TitleBar setDrawableSize(int px) {
        mDrawableSize = px;
        setLeftIcon(getLeftIcon());
        setRightIcon(getRightIcon());
        return this;
    }

    /**
     * 设置文字和图标的间距
     */
    public TitleBar setDrawablePadding(int px) {
        mLeftView.setCompoundDrawablePadding(px);
        mTitleView.setCompoundDrawablePadding(px);
        mRightView.setCompoundDrawablePadding(px);
        return this;
    }

    /**
     * 设置子 View 内间距
     */
    public TitleBar setChildPadding(int horizontal, int vertical) {
        mLeftView.setPadding(horizontal, vertical, horizontal, vertical);
        mTitleView.setPadding(horizontal, vertical, horizontal, vertical);
        mRightView.setPadding(horizontal, vertical, horizontal, vertical);
        return this;
    }

    /**
     * 获取左标题View对象
     */
    public TextView getLeftView() {
        return mLeftView;
    }

    /**
     * 获取标题View对象
     */
    public TextView getTitleView() {
        return mTitleView;
    }

    /**
     * 获取右标题View对象
     */
    public TextView getRightView() {
        return mRightView;
    }

    /**
     * 获取分割线View对象
     */
    public View getLineView() {
        return mLineView;
    }

    /**
     * 获取当前的初始化器
     */
    public ITitleBarInitializer getCurrentInitializer() {
        return mCurrentInitializer;
    }

    /**
     * 设置默认初始化器
     */
    public static void setDefaultInitializer(ITitleBarInitializer initializer) {
        TitleBar.sGlobalInitializer = initializer;
    }
}