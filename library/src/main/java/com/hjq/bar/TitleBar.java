package com.hjq.bar;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hjq.bar.style.BaseTitleBarStyle;
import com.hjq.bar.style.TitleBarLightStyle;
import com.hjq.bar.style.TitleBarNightStyle;
import com.hjq.bar.style.TitleBarRippleStyle;
import com.hjq.bar.style.TitleBarTransparentStyle;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/TitleBar
 *    time   : 2018/08/17
 *    desc   : Android 通用标题栏
 */
public class TitleBar extends FrameLayout
        implements View.OnClickListener, Runnable {

    /** 默认样式 */
    private static ITitleBarStyle sDefaultStyle;

    /** 当前样式 */
    private ITitleBarStyle mCurrentStyle;
    /** 监听器对象 */
    private OnTitleBarListener mListener;

    private LinearLayout mMainLayout;
    private TextView mLeftView, mTitleView, mRightView;
    private View mLineView;

    /** 标记是否初始化完成 */
    private boolean mInitialize;

    public TitleBar(Context context) {
        this(context, null, 0);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mMainLayout = ViewCore.newMainLayout(context);
        mLineView = ViewCore.newLineView(context);
        mLeftView = ViewCore.newLeftView(context);
        mTitleView = ViewCore.newTitleView(context);
        mRightView = ViewCore.newRightView(context);

        mLeftView.setEnabled(false);
        mTitleView.setEnabled(false);
        mRightView.setEnabled(false);

        // 判断默认样式是否为空
        if (sDefaultStyle == null) {
            // 由于默认样式是静态的，所以必须使用 Application 作为上下文
            sDefaultStyle = new TitleBarLightStyle(getContext().getApplicationContext());
        }

        final TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.TitleBar);

        // 标题栏样式设置
        switch (array.getInt(R.styleable.TitleBar_barStyle, 0)) {
            case 0x10:
                mCurrentStyle = new TitleBarLightStyle(getContext());
                break;
            case 0x20:
                mCurrentStyle = new TitleBarNightStyle(getContext());
                break;
            case 0x30:
                mCurrentStyle = new TitleBarTransparentStyle(getContext());
                break;
            case 0x40:
                mCurrentStyle = new TitleBarRippleStyle(getContext());
                break;
            default:
                mCurrentStyle = TitleBar.sDefaultStyle;
                break;
        }

        // 设置子 View 内间距
        setChildPadding(sDefaultStyle.getChildPadding());

        // 标题设置
        if (array.hasValue(R.styleable.TitleBar_leftTitle)) {
            setLeftTitle(array.getString(R.styleable.TitleBar_leftTitle));
        }

        if (array.hasValue(R.styleable.TitleBar_title)) {
            setTitle(array.getString(R.styleable.TitleBar_title));
        } else {
            // 如果当前上下文对象是Activity，就获取Activity的标题
            if (getContext() instanceof Activity) {
                // 获取清单文件中的 android:label 属性值
                CharSequence label = ((Activity) getContext()).getTitle();
                if (label != null && !"".equals(label.toString())) {
                    try {
                        PackageManager packageManager = getContext().getPackageManager();
                        PackageInfo packageInfo = packageManager.getPackageInfo(getContext().getPackageName(), 0);
                        //如果当前 Activity 没有设置 android:label 属性，则默认会返回 APP 名称，则需要过滤掉
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
            setLeftIcon(ViewCore.getDrawable(getContext(), array.getResourceId(R.styleable.TitleBar_leftIcon, 0)));
        } else {
            if (array.getBoolean(R.styleable.TitleBar_backButton, mCurrentStyle.getBackIcon() != null)) {
                // 显示默认的返回图标
                setLeftIcon(mCurrentStyle.getBackIcon());
            }
        }

        if (array.hasValue(R.styleable.TitleBar_rightIcon)) {
            setRightIcon(ViewCore.getDrawable(getContext(), array.getResourceId(R.styleable.TitleBar_rightIcon, 0)));
        }

        // 文字颜色设置
        setLeftColor(array.getColor(R.styleable.TitleBar_leftColor, mCurrentStyle.getLeftColor()));
        setTitleColor(array.getColor(R.styleable.TitleBar_titleColor, mCurrentStyle.getTitleColor()));
        setRightColor(array.getColor(R.styleable.TitleBar_rightColor, mCurrentStyle.getRightColor()));

        // 文字大小设置
        setLeftSize(TypedValue.COMPLEX_UNIT_PX, array.getDimensionPixelSize(R.styleable.TitleBar_leftSize, (int) mCurrentStyle.getLeftSize()));
        setTitleSize(TypedValue.COMPLEX_UNIT_PX, array.getDimensionPixelSize(R.styleable.TitleBar_titleSize, (int) mCurrentStyle.getTitleSize()));
        setRightSize(TypedValue.COMPLEX_UNIT_PX, array.getDimensionPixelSize(R.styleable.TitleBar_rightSize, (int) mCurrentStyle.getRightSize()));

        // 背景设置
        if (array.hasValue(R.styleable.TitleBar_leftBackground)) {
            setLeftBackground(array.getDrawable(R.styleable.TitleBar_leftBackground));
        } else {
            setLeftBackground(mCurrentStyle.getLeftBackground());
        }

        if (array.hasValue(R.styleable.TitleBar_rightBackground)) {
            setRightBackground(array.getDrawable(R.styleable.TitleBar_rightBackground));
        } else {
            setRightBackground(mCurrentStyle.getRightBackground());
        }

        // 分割线设置
        if (array.hasValue(R.styleable.TitleBar_lineColor)) {
            setLineDrawable(array.getDrawable(R.styleable.TitleBar_lineColor));
        } else {
            setLineDrawable(mCurrentStyle.getLineDrawable());
        }

        setTitleGravity(array.getInt(R.styleable.TitleBar_titleGravity, mCurrentStyle.getTitleGravity()));
        setLineVisible(array.getBoolean(R.styleable.TitleBar_lineVisible, mCurrentStyle.isLineVisible()));
        setLineSize(array.getDimensionPixelSize(R.styleable.TitleBar_lineSize, mCurrentStyle.getLineSize()));
        // 设置文字和图标之间的间距
        setDrawablePadding(array.getDimensionPixelSize(R.styleable.TitleBar_android_drawablePadding, mCurrentStyle.getDrawablePadding()));

        // 回收TypedArray
        array.recycle();

        // 设置默认背景
        if (getBackground() == null) {
            ViewCore.setBackground(this, mCurrentStyle.getBackground());
        }

        mMainLayout.addView(mLeftView);
        mMainLayout.addView(mTitleView);
        mMainLayout.addView(mRightView);

        addView(mMainLayout, 0);
        addView(mLineView, 1);

        // 标记初始化完成
        mInitialize = true;
        post(this);
    }

    @Override
    public void setLayoutParams(ViewGroup.LayoutParams params) {
        // 如果当前宽度是自适应则转换成占满父布局
        if (params.width == LayoutParams.WRAP_CONTENT) {
            params.width = LayoutParams.MATCH_PARENT;
        }

        // 如果当前高度是自适应则获取默认设定高度
        if (params.height == ViewGroup.LayoutParams.WRAP_CONTENT) {
            mMainLayout.setLayoutParams(new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, mCurrentStyle.getTitleBarHeight()));
        }
        super.setLayoutParams(params);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new FrameLayout.LayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
    }

    @Override
    protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams params) {
        return new FrameLayout.LayoutParams(params);
    }

    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams params) {
        return params instanceof FrameLayout.LayoutParams;
    }

    @Override
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    @Override
    public void run() {
        // 当前必须已经初始化完成
        if (!mInitialize) {
            return;
        }

        // 当前标题 View 的重心必须是水平居中的
        if ((mTitleView.getGravity() & Gravity.CENTER_HORIZONTAL) != 0) {
            // 更新中间标题的内边距，避免向左或者向右偏移
            final int leftSize = mLeftView.getWidth();
            final int rightSize = mRightView.getWidth();
            if (leftSize != rightSize) {
                if (leftSize > rightSize) {
                    mTitleView.setPadding(0, 0, leftSize - rightSize, 0);
                } else {
                    mTitleView.setPadding(rightSize - leftSize, 0, 0, 0);
                }
            }
        }

        // 更新 View 状态
        mLeftView.setEnabled(ViewCore.hasTextViewContent(mLeftView));
        mTitleView.setEnabled(ViewCore.hasTextViewContent(mTitleView));
        mRightView.setEnabled(ViewCore.hasTextViewContent(mRightView));
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

    /**
     * 设置点击监听器
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
     * 标题
     */
    public TitleBar setTitle(int id) {
        return setTitle(getResources().getString(id));
    }

    public TitleBar setTitle(CharSequence text) {
        mTitleView.setText(text);
        post(this);
        return this;
    }

    public CharSequence getTitle() {
        return mTitleView.getText();
    }

    /**
     * 左边的标题
     */
    public TitleBar setLeftTitle(int id) {
        return setLeftTitle(getResources().getString(id));
    }

    public TitleBar setLeftTitle(CharSequence text) {
        mLeftView.setText(text);
        post(this);
        return this;
    }

    public CharSequence getLeftTitle() {
        return mLeftView.getText();
    }

    /**
     * 右边的标题
     */
    public TitleBar setRightTitle(int id) {
        return setRightTitle(getResources().getString(id));
    }

    public TitleBar setRightTitle(CharSequence text) {
        mRightView.setText(text);
        post(this);
        return this;
    }

    public CharSequence getRightTitle() {
        return mRightView.getText();
    }

    /**
     * 设置左边的图标
     */
    public TitleBar setLeftIcon(int id) {
        return setLeftIcon(ViewCore.getDrawable(getContext(), id));
    }

    public TitleBar setLeftIcon(Drawable drawable) {
        mLeftView.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
        post(this);
        return this;
    }

    public Drawable getLeftIcon() {
        return mLeftView.getCompoundDrawables()[0];
    }

    /**
     * 设置右边的图标
     */
    public TitleBar setRightIcon(int id) {
        return setRightIcon(ViewCore.getDrawable(getContext(), id));
    }

    public TitleBar setRightIcon(Drawable drawable) {
        mRightView.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
        post(this);
        return this;
    }

    public Drawable getRightIcon() {
        return mRightView.getCompoundDrawables()[2];
    }

    /**
     * 设置左标题颜色
     */
    public TitleBar setLeftColor(int color) {
        mLeftView.setTextColor(color);
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
        return setLeftBackground(ViewCore.getDrawable(getContext(), id));
    }

    public TitleBar setLeftBackground(Drawable drawable) {
        ViewCore.setBackground(mLeftView, drawable);
        post(this);
        return this;
    }

    /**
     * 设置右标题状态选择器
     */
    public TitleBar setRightBackground(int id) {
        return setRightBackground(ViewCore.getDrawable(getContext(), id));
    }

    public TitleBar setRightBackground(Drawable drawable) {
        ViewCore.setBackground(mRightView, drawable);
        post(this);
        return this;
    }

    /**
     * 设置左标题的文本大小
     */
    public TitleBar setLeftSize(int unit, float size) {
        mLeftView.setTextSize(unit, size);
        post(this);
        return this;
    }

    /**
     * 设置标题的文本大小
     */
    public TitleBar setTitleSize(int unit, float size) {
        mTitleView.setTextSize(unit, size);
        post(this);
        return this;
    }

    /**
     * 设置右标题的文本大小
     */
    public TitleBar setRightSize(int unit, float size) {
        mRightView.setTextSize(unit, size);
        post(this);
        return this;
    }

    /**
     * 设置分割线是否显示
     */
    public TitleBar setLineVisible(boolean visible) {
        mLineView.setVisibility(visible ? VISIBLE : GONE);
        return this;
    }

    /**
     * 设置分割线的颜色
     */
    public TitleBar setLineColor(int color) {
        return setLineDrawable(new ColorDrawable(color));
    }
    public TitleBar setLineDrawable(Drawable drawable) {
        ViewCore.setBackground(mLineView, drawable);
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
    public TitleBar setTitleGravity(int gravity) {
        // 适配 Android 4.2 新特性，布局反方向（开发者选项 - 强制使用从右到左的布局方向）
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            gravity = Gravity.getAbsoluteGravity(gravity, getResources().getConfiguration().getLayoutDirection());
        }
        mTitleView.setGravity(gravity);
        return this;
    }

    /**
     * 设置文字和图标的间距
     */
    public TitleBar setDrawablePadding(int px) {
        mLeftView.setCompoundDrawablePadding(px);
        mTitleView.setCompoundDrawablePadding(px);
        mRightView.setCompoundDrawablePadding(px);
        post(this);
        return this;
    }

    /**
     * 设置子 View 内间距
     */
    public TitleBar setChildPadding(int px) {
        mLeftView.setPadding(px, 0, px, 0);
        mTitleView.setPadding(px, 0, px, 0);
        mRightView.setPadding(px, 0, px, 0);
        post(this);
        return this;
    }

    /**
     * 获取主要的布局对象
     */
    public LinearLayout getMainLayout() {
        return mMainLayout;
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
     * 初始化全局的TitleBar样式
     *
     * @param style         样式实现类，框架已经实现三种不同的样式
     *                      日间模式：{@link TitleBarLightStyle}
     *                      夜间模式：{@link TitleBarNightStyle}
     *                      透明模式：{@link TitleBarTransparentStyle}
     *                      水波纹模式：{@link TitleBarRippleStyle}
     */
    public static void initStyle(ITitleBarStyle style) {
        TitleBar.sDefaultStyle = style;
        // Context 检查
        if (style instanceof BaseTitleBarStyle) {
            Context context = ((BaseTitleBarStyle) style).getContext();
            // 当前必须用 Application 的上下文创建的，否则可能会导致内存泄露
            if (!(context instanceof Application)) {
                throw new IllegalArgumentException("The view must be initialized using the context of the application");
            }
        }
    }

    public ITitleBarStyle getCurrentStyle() {
        return mCurrentStyle;
    }
}