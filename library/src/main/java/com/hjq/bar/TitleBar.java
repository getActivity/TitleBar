package com.hjq.bar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.hjq.bar.style.LightBarStyle;
import com.hjq.bar.style.NightBarStyle;
import com.hjq.bar.style.RippleBarStyle;
import com.hjq.bar.style.TransparentBarStyle;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/TitleBar
 *    time   : 2018/08/17
 *    desc   : 标题栏框架
 */
@SuppressWarnings({"unused", "UnusedReturnValue"})
public class TitleBar extends FrameLayout
        implements View.OnClickListener,
        View.OnLayoutChangeListener {

    /** 默认初始化器 */
    private static ITitleBarStyle sGlobalStyle;
    /** 当前初始化器 */
    private final ITitleBarStyle mCurrentStyle;

    /** 监听器对象 */
    private OnTitleBarListener mListener;

    /** 标题栏子控件 */
    private final TextView mLeftView, mTitleView, mRightView;
    private final View mLineView;

    /** 控件内间距 */
    private int mHorizontalPadding, mVerticalPadding;

    /** 图标显示大小 */
    private int mLeftIconWidth, mLeftIconHeight;
    private int mTitleIconWidth, mTitleIconHeight;
    private int mRightIconWidth, mRightIconHeight;

    /** 图标显示重心 */
    private int mLeftIconGravity, mTitleIconGravity, mRightIconGravity;

    public TitleBar(Context context) {
        this(context, null);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        if (sGlobalStyle == null) {
            sGlobalStyle = new LightBarStyle();
        }

        final TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.TitleBar, 0, R.style.TitleBarStyle);

        // 标题栏样式设置
        switch (array.getInt(R.styleable.TitleBar_barStyle, 0)) {
            case 0x10:
                mCurrentStyle = new LightBarStyle();
                break;
            case 0x20:
                mCurrentStyle = new NightBarStyle();
                break;
            case 0x30:
                mCurrentStyle = new TransparentBarStyle();
                break;
            case 0x40:
                mCurrentStyle = new RippleBarStyle();
                break;
            default:
                mCurrentStyle = TitleBar.sGlobalStyle;
                break;
        }

        mTitleView = mCurrentStyle.createTitleView(context);
        mLeftView = mCurrentStyle.createLeftView(context);
        mRightView = mCurrentStyle.createRightView(context);
        mLineView = mCurrentStyle.createLineView(context);

        mTitleView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT, Gravity.CENTER_HORIZONTAL));
        mLeftView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT, Gravity.START));
        mRightView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT, Gravity.END));
        mLineView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mCurrentStyle.getLineSize(getContext()), Gravity.BOTTOM));

        // 设置图标显示的重心
        setLeftIconGravity(array.getInt(R.styleable.TitleBar_leftIconGravity, mCurrentStyle.getLeftIconGravity(getContext())));
        setTitleIconGravity(array.getInt(R.styleable.TitleBar_titleIconGravity, mCurrentStyle.getTitleIconGravity(getContext())));
        setRightIconGravity(array.getInt(R.styleable.TitleBar_rightIconGravity, mCurrentStyle.getRightIconGravity(getContext())));

        // 设置图标显示的大小
        setLeftIconSize(array.getDimensionPixelSize(R.styleable.TitleBar_leftIconWidth, mCurrentStyle.getLeftIconWidth(getContext())),
                array.getDimensionPixelSize(R.styleable.TitleBar_leftIconHeight, mCurrentStyle.getLeftIconHeight(getContext())));
        setTitleIconSize(array.getDimensionPixelSize(R.styleable.TitleBar_titleIconWidth, mCurrentStyle.getTitleIconWidth(getContext())),
                array.getDimensionPixelSize(R.styleable.TitleBar_titleIconHeight, mCurrentStyle.getTitleIconHeight(getContext())));
        setRightIconSize(array.getDimensionPixelSize(R.styleable.TitleBar_rightIconWidth, mCurrentStyle.getRightIconWidth(getContext())),
                array.getDimensionPixelSize(R.styleable.TitleBar_rightIconHeight, mCurrentStyle.getRightIconHeight(getContext())));

        // 设置文字和图标之间的间距
        setLeftIconPadding(array.getDimensionPixelSize(R.styleable.TitleBar_leftIconPadding, mCurrentStyle.getLeftIconPadding(getContext())));
        setTitleIconPadding(array.getDimensionPixelSize(R.styleable.TitleBar_titleIconPadding, mCurrentStyle.getTitleIconPadding(getContext())));
        setRightIconPadding(array.getDimensionPixelSize(R.styleable.TitleBar_rightIconPadding, mCurrentStyle.getRightIconPadding(getContext())));

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
            if (array.getResourceId(R.styleable.TitleBar_leftIcon, 0) == R.drawable.bar_drawable_placeholder) {
                setLeftIcon(mCurrentStyle.getBackButtonDrawable(getContext()));
            } else {
                setLeftIcon(TitleBarSupport.getDrawable(getContext(), array.getResourceId(R.styleable.TitleBar_leftIcon, 0)));
            }
        }

        if (array.hasValue(R.styleable.TitleBar_titleIcon)) {
            setTitleIcon(TitleBarSupport.getDrawable(getContext(), array.getResourceId(R.styleable.TitleBar_titleIcon, 0)));
        }

        if (array.hasValue(R.styleable.TitleBar_rightIcon)) {
            setRightIcon(TitleBarSupport.getDrawable(getContext(), array.getResourceId(R.styleable.TitleBar_rightIcon, 0)));
        }

        // 图标着色设置
        if (array.hasValue(R.styleable.TitleBar_leftIconTint)) {
            setLeftIconTint(array.getColor(R.styleable.TitleBar_leftIconTint, 0));
        }

        if (array.hasValue(R.styleable.TitleBar_titleIconTint)) {
            setTitleIconTint(array.getColor(R.styleable.TitleBar_titleIconTint, 0));
        }

        if (array.hasValue(R.styleable.TitleBar_rightIconTint)) {
            setRightIconTint(array.getColor(R.styleable.TitleBar_rightIconTint, 0));
        }

        // 文字颜色设置
        setLeftTitleColor(array.getColor(R.styleable.TitleBar_leftTitleColor, mCurrentStyle.getLeftTitleColor(getContext())));
        setTitleColor(array.getColor(R.styleable.TitleBar_titleColor, mCurrentStyle.getTitleTitleColor(getContext())));
        setRightTitleColor(array.getColor(R.styleable.TitleBar_rightTitleColor, mCurrentStyle.getRightTitleColor(getContext())));

        // 文字大小设置
        setLeftTitleSize(TypedValue.COMPLEX_UNIT_PX, array.getDimensionPixelSize(R.styleable.TitleBar_leftTitleSize, mCurrentStyle.getLeftTitleSize(getContext())));
        setTitleSize(TypedValue.COMPLEX_UNIT_PX, array.getDimensionPixelSize(R.styleable.TitleBar_titleSize, mCurrentStyle.getTitleTitleSize(getContext())));
        setRightTitleSize(TypedValue.COMPLEX_UNIT_PX, array.getDimensionPixelSize(R.styleable.TitleBar_rightTitleSize, mCurrentStyle.getRightTitleSize(getContext())));

        // 背景设置
        if (array.hasValue(R.styleable.TitleBar_android_background)) {
            if (array.getResourceId(R.styleable.TitleBar_android_background, 0) == R.drawable.bar_drawable_placeholder) {
                TitleBarSupport.setBackground(this, mCurrentStyle.getTitleBarBackground(context));
            }
        }

        if (array.hasValue(R.styleable.TitleBar_leftBackground)) {
            if (array.getResourceId(R.styleable.TitleBar_leftBackground, 0) == R.drawable.bar_drawable_placeholder) {
                setLeftBackground(mCurrentStyle.getLeftTitleBackground(getContext()));
            } else {
                setLeftBackground(array.getDrawable(R.styleable.TitleBar_leftBackground));
            }
        }

        if (array.hasValue(R.styleable.TitleBar_rightBackground)) {
            if (array.getResourceId(R.styleable.TitleBar_rightBackground, 0) == R.drawable.bar_drawable_placeholder) {
                setRightBackground(mCurrentStyle.getRightTitleBackground(getContext()));
            } else {
                setRightBackground(array.getDrawable(R.styleable.TitleBar_rightBackground));
            }
        }

        // 分割线设置
        if (array.hasValue(R.styleable.TitleBar_lineDrawable)) {
            setLineDrawable(array.getDrawable(R.styleable.TitleBar_lineDrawable));
        } else {
            setLineDrawable(mCurrentStyle.getLineDrawable(getContext()));
        }

        if (array.hasValue(R.styleable.TitleBar_titleGravity)) {
            setTitleGravity(array.getInt(R.styleable.TitleBar_titleGravity, Gravity.NO_GRAVITY));
        }

        if (array.hasValue(R.styleable.TitleBar_leftTitleStyle)) {
            setLeftTitleStyle(Typeface.defaultFromStyle(array.getInt(R.styleable.TitleBar_leftTitleStyle, Typeface.NORMAL)));
        }

        if (array.hasValue(R.styleable.TitleBar_titleStyle)) {
            setTitleStyle(Typeface.defaultFromStyle(array.getInt(R.styleable.TitleBar_titleStyle, Typeface.NORMAL)));
        }

        if (array.hasValue(R.styleable.TitleBar_rightTitleStyle)) {
            setRightTitleStyle(Typeface.defaultFromStyle(array.getInt(R.styleable.TitleBar_rightTitleStyle, Typeface.NORMAL)));
        }

        if (array.hasValue(R.styleable.TitleBar_lineVisible)) {
            setLineVisible(array.getBoolean(R.styleable.TitleBar_lineVisible, false));
        }

        if (array.hasValue(R.styleable.TitleBar_lineSize)) {
            setLineSize(array.getDimensionPixelSize(R.styleable.TitleBar_lineSize, 0));
        }

        // 如果设置了这两个属性，则将内间距置为空
        if (array.hasValue(R.styleable.TitleBar_childPaddingHorizontal) || array.hasValue(R.styleable.TitleBar_childPaddingVertical)) {
            setPadding(0, 0, 0, 0);
        }

        // 获取子 View 内间距
        mHorizontalPadding = array.getDimensionPixelSize(R.styleable.TitleBar_childPaddingHorizontal,
                mCurrentStyle.getChildHorizontalPadding(getContext()));
        mVerticalPadding = array.getDimensionPixelSize(R.styleable.TitleBar_childPaddingVertical,
                mCurrentStyle.getChildVerticalPadding(getContext()));

        // 回收 TypedArray 对象
        array.recycle();

        addView(mTitleView, 0);
        addView(mLeftView, 1);
        addView(mRightView, 2);
        addView(mLineView, 3);

        addOnLayoutChangeListener(this);

        // 如果当前是布局预览模式
        if (isInEditMode()) {
            measure(0, 0);
            mTitleView.measure(0, 0);
            mLeftView.measure(0, 0);
            mRightView.measure(0, 0);
            int horizontalMargin = Math.max(mLeftView.getMeasuredWidth(), mRightView.getMeasuredWidth()) + mHorizontalPadding;
            MarginLayoutParams layoutParams = (MarginLayoutParams) mTitleView.getLayoutParams();
            layoutParams.setMargins(horizontalMargin, 0, horizontalMargin, 0);
        }
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
            mLeftView.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
                    MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            mTitleView.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
                    MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            mRightView.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
                    MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
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
        mLeftView.setEnabled(TitleBarSupport.isContainContent(mLeftView));
        mTitleView.setEnabled(TitleBarSupport.isContainContent(mTitleView));
        mRightView.setEnabled(TitleBarSupport.isContainContent(mRightView));

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
    public void onClick(View view) {
        if (mListener == null) {
            return;
        }

        if (view == mLeftView) {
            mListener.onLeftClick(view);
        } else if (view == mRightView) {
            mListener.onRightClick(view);
        } else if (view == mTitleView) {
            mListener.onTitleClick(view);
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
    public TitleBar setOnTitleBarListener(OnTitleBarListener listener) {
        mListener = listener;
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
     * 设置左图标色彩
     */
    public TitleBar setLeftIconTint(int color) {
        TitleBarSupport.setDrawableTint(getLeftIcon(), color);
        return this;
    }

    /**
     * 设置左图标
     */
    public TitleBar setLeftIcon(int id) {
        return setLeftIcon(TitleBarSupport.getDrawable(getContext(), id));
    }

    public TitleBar setLeftIcon(Drawable drawable) {
        TitleBarSupport.setDrawableSize(drawable, mLeftIconWidth, mLeftIconHeight);
        TitleBarSupport.setTextCompoundDrawable(mLeftView, drawable, mLeftIconGravity);
        return this;
    }

    /**
     * 获取左图标
     */
    public Drawable getLeftIcon() {
        return TitleBarSupport.getTextCompoundDrawable(mLeftView, mLeftIconGravity);
    }

    /**
     * 设置左边文字和图标的间距
     */
    public TitleBar setLeftIconPadding(int padding) {
        mLeftView.setCompoundDrawablePadding(padding);
        return this;
    }

    /**
     * 设置左图标色彩
     */
    public TitleBar setTitleIconTint(int color) {
        TitleBarSupport.setDrawableTint(getTitleIcon(), color);
        return this;
    }

    /**
     * 设置左图标
     */
    public TitleBar setTitleIcon(int id) {
        return setTitleIcon(TitleBarSupport.getDrawable(getContext(), id));
    }

    public TitleBar setTitleIcon(Drawable drawable) {
        TitleBarSupport.setDrawableSize(drawable, mTitleIconWidth, mTitleIconHeight);
        TitleBarSupport.setTextCompoundDrawable(mTitleView, drawable, mTitleIconGravity);
        return this;
    }

    /**
     * 获取标题图标
     */
    public Drawable getTitleIcon() {
        return TitleBarSupport.getTextCompoundDrawable(mTitleView, mTitleIconGravity);
    }

    /**
     * 设置中间文字和图标的间距
     */
    public TitleBar setTitleIconPadding(int padding) {
        mTitleView.setCompoundDrawablePadding(padding);
        return this;
    }

    /**
     * 设置右图标色彩
     */
    public TitleBar setRightIconTint(int color) {
        TitleBarSupport.setDrawableTint(getRightIcon(), color);
        return this;
    }

    /**
     * 设置右图标
     */
    public TitleBar setRightIcon(int id) {
        return setRightIcon(TitleBarSupport.getDrawable(getContext(), id));
    }

    public TitleBar setRightIcon(Drawable drawable) {
        TitleBarSupport.setDrawableSize(drawable, mRightIconWidth, mRightIconHeight);
        TitleBarSupport.setTextCompoundDrawable(mRightView, drawable, mRightIconGravity);
        return this;
    }

    /**
     * 获取右图标
     */
    public Drawable getRightIcon() {
        return TitleBarSupport.getTextCompoundDrawable(mRightView, mRightIconGravity);
    }

    /**
     * 设置右边文字和图标的间距
     */
    public TitleBar setRightIconPadding(int padding) {
        mRightView.setCompoundDrawablePadding(padding);
        return this;
    }

    /**
     * 设置左边图标显示大小
     */
    public TitleBar setLeftIconSize(int width, int height) {
        mLeftIconWidth = width;
        mLeftIconHeight = height;
        TitleBarSupport.setDrawableSize(getLeftIcon(), width, height);
        return this;
    }

    /**
     * 设置中间图标显示大小
     */
    public TitleBar setTitleIconSize(int width, int height) {
        mTitleIconWidth = width;
        mTitleIconHeight = height;
        TitleBarSupport.setDrawableSize(getTitleIcon(), width, height);
        return this;
    }

    /**
     * 设置右边图标显示大小
     */
    public TitleBar setRightIconSize(int width, int height) {
        mRightIconWidth = width;
        mRightIconHeight = height;
        TitleBarSupport.setDrawableSize(getRightIcon(), width, height);
        return this;
    }

    /**
     * 设置左边标题的图标重心
     */
    public TitleBar setLeftIconGravity(int gravity) {
        Drawable drawable = getLeftIcon();
        mLeftIconGravity = gravity;
        if (drawable != null) {
            TitleBarSupport.setTextCompoundDrawable(mLeftView, drawable, gravity);
        }
        return this;
    }

    /**
     * 设置中间标题的图标重心
     */
    public TitleBar setTitleIconGravity(int gravity) {
        Drawable drawable = getTitleIcon();
        mTitleIconGravity = gravity;
        if (drawable != null) {
            TitleBarSupport.setTextCompoundDrawable(mTitleView, drawable, gravity);
        }
        return this;
    }

    /**
     * 设置右边标题的图标重心
     */
    public TitleBar setRightIconGravity(int gravity) {
        Drawable drawable = getRightIcon();
        mRightIconGravity = gravity;
        if (drawable != null) {
            TitleBarSupport.setTextCompoundDrawable(mRightView, drawable, gravity);
        }
        return this;
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
    public TitleBar setLeftTitleColor(int color) {
        mLeftView.setTextColor(color);
        return this;
    }

    /**
     * 设置右标题颜色
     */
    public TitleBar setRightTitleColor(int color) {
        mRightView.setTextColor(color);
        return this;
    }

    /**
     * 设置左标题状态选择器
     */
    public TitleBar setLeftBackground(int id) {
        return setLeftBackground(TitleBarSupport.getDrawable(getContext(), id));
    }

    public TitleBar setLeftBackground(Drawable drawable) {
        TitleBarSupport.setBackground(mLeftView, drawable);
        return this;
    }

    /**
     * 设置右标题状态选择器
     */
    public TitleBar setRightBackground(int id) {
        return setRightBackground(TitleBarSupport.getDrawable(getContext(), id));
    }

    public TitleBar setRightBackground(Drawable drawable) {
        TitleBarSupport.setBackground(mRightView, drawable);
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
    public TitleBar setLeftTitleSize(int unit, float size) {
        mLeftView.setTextSize(unit, size);
        return this;
    }

    /**
     * 设置右标题文字大小
     */
    public TitleBar setRightTitleSize(int unit, float size) {
        mRightView.setTextSize(unit, size);
        return this;
    }

    /**
     * 设置左标题的文字样式
     */
    public TitleBar setLeftTitleStyle(Typeface typeface) {
        mLeftView.setTypeface(typeface);
        return this;
    }

    /**
     * 设置中间标题的文字样式
     */
    public TitleBar setTitleStyle(Typeface typeface) {
        mTitleView.setTypeface(typeface);
        return this;
    }

    /**
     * 设置右边标题的文字样式
     */
    public TitleBar setRightTitleStyle(Typeface typeface) {
        mRightView.setTypeface(typeface);
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
        TitleBarSupport.setBackground(mLineView, drawable);
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
        gravity = TitleBarSupport.getAbsoluteGravity(this, gravity);

        // 如果标题的重心为左，那么左边就不能有内容
        if (gravity == Gravity.LEFT &&
                TitleBarSupport.isContainContent(TitleBarSupport.isLayoutRtl(getContext()) ? mRightView : mLeftView)) {
            throw new IllegalStateException("Title center of gravity for the left, the left title can not have content");
        }

        // 如果标题的重心为右，那么右边就不能有内容
        if (gravity == Gravity.RIGHT &&
                TitleBarSupport.isContainContent(TitleBarSupport.isLayoutRtl(getContext()) ? mLeftView : mRightView)) {
            throw new IllegalStateException("Title center of gravity for the right, the right title can not have content");
        }

        LayoutParams params = (LayoutParams) mTitleView.getLayoutParams();
        params.gravity = gravity;
        mTitleView.setLayoutParams(params);
        return this;
    }

    /**
     * 设置子 View 内间距
     */
    public TitleBar setChildPadding(int horizontalPadding, int verticalPadding) {
        mHorizontalPadding = horizontalPadding;
        mVerticalPadding = verticalPadding;
        mLeftView.setPadding(horizontalPadding, verticalPadding, horizontalPadding, verticalPadding);
        mTitleView.setPadding(horizontalPadding, verticalPadding, horizontalPadding, verticalPadding);
        mRightView.setPadding(horizontalPadding, verticalPadding, horizontalPadding, verticalPadding);
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
    public ITitleBarStyle getCurrentStyle() {
        return mCurrentStyle;
    }

    /**
     * 设置默认初始化器
     */
    public static void setDefaultStyle(ITitleBarStyle style) {
        sGlobalStyle = style;
    }
}