package com.hjq.bar;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 *    author : HJQ
 *    github : https://github.com/getActivity/TitleBar
 *    time   : 2018/08/17
 *    desc   : Android 通用标题栏
 */
public class TitleBar extends FrameLayout
        implements View.OnClickListener, Runnable {

    private static ITitleBarStyle sDefaultValue = new DefaultStyle();

    private OnTitleBarListener mListener;

    private LinearLayout mMainLayout;
    private TextView mLeftView;
    private TextView mTitleView;
    private TextView mRightView;

    private View mLineView;

    public TitleBar(Context context) {
        this(context, null, 0);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
        initStyle(attrs);
    }

    private void initView(Context context) {
        ViewBuilder builder = new ViewBuilder(context);
        mMainLayout = builder.getMainLayout();
        mLineView = builder.getLineView();
        mTitleView = builder.getTitleView();
        mLeftView = builder.getLeftView();
        mRightView = builder.getRightView();

        mMainLayout.addView(mLeftView);
        mMainLayout.addView(mTitleView);
        mMainLayout.addView(mRightView);

        addView(mMainLayout, 0);
        addView(mLineView, 1);
    }

    private void initStyle(AttributeSet attrs) {

        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.TitleBar);

        //标题设置

        if (ta.hasValue(R.styleable.TitleBar_title_left)) {
            setLeftTitle(ta.getString(R.styleable.TitleBar_title_left));
        }

        if (ta.hasValue(R.styleable.TitleBar_title)) {
            setTitle(ta.getString(R.styleable.TitleBar_title));
        } else {
            //如果当前上下文对象是Activity，就获取Activity的标题
            if (getContext() instanceof Activity) {
                //获取清单文件中的label属性值
                CharSequence label = ((Activity) getContext()).getTitle();
                //如果Activity没有设置label属性，则默认会返回APP名称，需要过滤掉
                if (label != null && !label.toString().equals("")) {

                    try {
                        PackageManager packageManager = getContext().getPackageManager();
                        PackageInfo packageInfo = packageManager.getPackageInfo(
                                getContext().getPackageName(), 0);

                        if (!label.toString().equals(packageInfo.applicationInfo.loadLabel(packageManager).toString())) {
                            setTitle(label);
                        }
                    } catch (PackageManager.NameNotFoundException ignored) {}
                }
            }
        }

        if (ta.hasValue(R.styleable.TitleBar_title_right)) {
            setRightTitle(ta.getString(R.styleable.TitleBar_title_right));
        }

        // 图标设置

        if (ta.hasValue(R.styleable.TitleBar_icon_left)) {
            setLeftIcon(getContext().getResources().getDrawable(ta.getResourceId(R.styleable.TitleBar_icon_left, 0)));
        } else {
            // 显示返回图标
            if (ta.getBoolean(R.styleable.TitleBar_icon_back, true)) {
                setLeftIcon(getContext().getResources().getDrawable(sDefaultValue.getBackIconResource()));
            }
        }

        if (ta.hasValue(R.styleable.TitleBar_icon_right)) {
            setRightIcon(getContext().getResources().getDrawable(ta.getResourceId(R.styleable.TitleBar_icon_right, 0)));
        }

        //文字颜色设置
        mLeftView.setTextColor(ta.getColor(R.styleable.TitleBar_color_left, sDefaultValue.getLeftViewColor()));
        mTitleView.setTextColor(ta.getColor(R.styleable.TitleBar_color_title, sDefaultValue.getTitleViewColor()));
        mRightView.setTextColor(ta.getColor(R.styleable.TitleBar_color_right, sDefaultValue.getRightViewColor()));

        //文字大小设置
        mLeftView.setTextSize(TypedValue.COMPLEX_UNIT_PX, ta.getDimensionPixelSize(R.styleable.TitleBar_size_left, ViewBuilder.sp2px(getContext(), sDefaultValue.getLeftViewSize())));
        mTitleView.setTextSize(TypedValue.COMPLEX_UNIT_PX, ta.getDimensionPixelSize(R.styleable.TitleBar_size_title, ViewBuilder.sp2px(getContext(), sDefaultValue.getTitleViewSize())));
        mRightView.setTextSize(TypedValue.COMPLEX_UNIT_PX, ta.getDimensionPixelSize(R.styleable.TitleBar_size_right, ViewBuilder.sp2px(getContext(), sDefaultValue.getRightViewSize())));

        //背景设置
        mLeftView.setBackgroundResource(ta.getResourceId(R.styleable.TitleBar_background_left, sDefaultValue.getLeftViewBackground()));
        mRightView.setBackgroundResource(ta.getResourceId(R.styleable.TitleBar_background_right, sDefaultValue.getRightViewBackground()));

        //分割线设置
        mLineView.setVisibility(ta.getBoolean(R.styleable.TitleBar_line, sDefaultValue.getLineVisibility()) ? View.VISIBLE : View.GONE);
        mLineView.setBackgroundColor(ta.getColor(R.styleable.TitleBar_color_line, sDefaultValue.getLineBackgroundColor()));

        //回收TypedArray
        ta.recycle();

        //设置默认背景
        if (getBackground() == null) {
            setBackgroundColor(sDefaultValue.getBackgroundColor());
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //设置TitleBar默认的高度
        if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.AT_MOST || MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.UNSPECIFIED) {
            super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(ViewBuilder.getActionBarHeight(getContext()), MeasureSpec.EXACTLY));
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    public void setTitle(CharSequence text) {
        mTitleView.setText(text);
        postDelayed(this, 100);
    }

    public void setLeftTitle(CharSequence text) {
        mLeftView.setText(text);
        postDelayed(this, 100);
    }

    public void setRightTitle(CharSequence text) {
        mRightView.setText(text);
        postDelayed(this, 100);
    }

    public void setLeftIcon(Drawable drawable) {
        mLeftView.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
        postDelayed(this, 100);
    }

    public void setRightIcon(Drawable drawable) {
        mRightView.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
        postDelayed(this, 100);
    }

    // Runnable

    @Override
    public void run() {
        //更新中间标题的内边距，避免向左或者向右偏移
        int leftSize = mLeftView.getWidth();
        int rightSize = mRightView.getWidth();
        if (leftSize != rightSize) {
            if (leftSize > rightSize) {
                mTitleView.setPadding(0, 0, leftSize - rightSize, 0);
            } else {
                mTitleView.setPadding(rightSize - leftSize, 0, 0, 0);
            }
        }

        //更新View状态
        if (!"".equals(mLeftView.getText().toString()) || mLeftView.getCompoundDrawables()[0] != null) {
            mLeftView.setEnabled(true);
        }
        if (!"".equals(mTitleView.getText().toString())) {
            mTitleView.setEnabled(true);
        }
        if (!"".equals(mRightView.getText().toString()) || mRightView.getCompoundDrawables()[2] != null) {
            mRightView.setEnabled(true);
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        //设置监听
        mTitleView.setOnClickListener(this);
        mLeftView.setOnClickListener(this);
        mRightView.setOnClickListener(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        //移除监听
        mTitleView.setOnClickListener(null);
        mLeftView.setOnClickListener(null);
        mRightView.setOnClickListener(null);
        super.onDetachedFromWindow();
    }

    public void setOnTitleBarListener(OnTitleBarListener l) {
        mListener = l;
    }

    // View.OnClickListener

    @Override
    public void onClick(View v) {
        if (mListener == null) return;

        if (v == mLeftView) {
            mListener.onLeftClick(v);
        } else if (v == mTitleView) {
            mListener.onTitleClick(v);
        } else if (v == mRightView) {
            mListener.onRightClick(v);
        }
    }

    public LinearLayout getMainLayout() {
        return mMainLayout;
    }

    public TextView getLeftView() {
        return mLeftView;
    }

    public TextView getTitleView() {
        return mTitleView;
    }

    public TextView getRightView() {
        return mRightView;
    }

    public View getLineView() {
        return mLineView;
    }

    public static void initStyle(ITitleBarStyle value) {
        TitleBar.sDefaultValue = value;
    }
}