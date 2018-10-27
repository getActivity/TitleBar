package com.hjq.bar;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 *    author : HJQ
 *    github : https://github.com/getActivity/TitleBar
 *    time   : 2018/08/20
 *    desc   : 标题栏子View构建器
 */
final class ViewBuilder {

    private LinearLayout mMainLayout;
    private TextView mLeftView;
    private TextView mTitleView;
    private TextView mRightView;

    private View mLineView;

    ViewBuilder(Context context) {
        mMainLayout = new LinearLayout(context);
        mMainLayout.setId(R.id.bar_id_main_layout);
        mMainLayout.setOrientation(LinearLayout.HORIZONTAL);
        mMainLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        mLeftView = new TextView(context);
        mLeftView.setId(R.id.bar_id_left_view);
        mLeftView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mLeftView.setPadding(dp2px(context, 12), 0, dp2px(context, 12), 0);
        mLeftView.setCompoundDrawablePadding(dp2px(context, 2));
        mLeftView.setGravity(Gravity.CENTER_VERTICAL);
        mLeftView.setSingleLine();
        mLeftView.setEllipsize(TextUtils.TruncateAt.END);

        mTitleView = new TextView(context);
        mTitleView.setId(R.id.bar_id_title_view);
        LinearLayout.LayoutParams titleParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        titleParams.weight = 1;
        titleParams.leftMargin = dp2px(context, 10);
        titleParams.rightMargin = dp2px(context, 10);
        mTitleView.setLayoutParams(titleParams);
        mTitleView.setGravity(Gravity.CENTER);
        mTitleView.setSingleLine();
        mTitleView.setEllipsize(TextUtils.TruncateAt.END);

        mRightView = new TextView(context);
        mRightView.setId(R.id.bar_id_right_view);
        mRightView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mRightView.setPadding(dp2px(context, 12), 0, dp2px(context, 12), 0);
        mRightView.setCompoundDrawablePadding(dp2px(context, 2));
        mRightView.setGravity(Gravity.CENTER_VERTICAL);
        mRightView.setSingleLine();
        mRightView.setEllipsize(TextUtils.TruncateAt.END);

        mLineView = new View(context);
        mLineView.setId(R.id.bar_id_line_view);
        FrameLayout.LayoutParams lineParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1);
        lineParams.gravity = Gravity.BOTTOM;
        mLineView.setLayoutParams(lineParams);
    }

    LinearLayout getMainLayout() {
        return mMainLayout;
    }

    View getLineView() {
        return mLineView;
    }

    TextView getLeftView() {
        return mLeftView;
    }

    TextView getTitleView() {
        return mTitleView;
    }

    TextView getRightView() {
        return mRightView;
    }

    /**
     * 获取ActionBar的高度
     */
    static int getActionBarHeight(Context context) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
            TypedArray ta = context.obtainStyledAttributes(new int[]{android.R.attr.actionBarSize});
            int actionBarSize = (int) ta.getDimension(0, 0);
            ta.recycle();
            if (actionBarSize > 0) return actionBarSize;
        }
        return ViewBuilder.dp2px(context, 100);
    }

    /**
     * 获取 Activity 的Label属性值
     */
    static CharSequence getActivityLabel(Activity activity) {
        //获取清单文件中的label属性值
        CharSequence label = activity.getTitle();
        //如果Activity没有设置label属性，则默认会返回APP名称，需要过滤掉
        if (label != null && !label.toString().equals("")) {

            try {
                PackageManager packageManager = activity.getPackageManager();
                PackageInfo packageInfo = packageManager.getPackageInfo(activity.getPackageName(), 0);

                if (!label.toString().equals(packageInfo.applicationInfo.loadLabel(packageManager).toString())) {
                    return label;
                }
            } catch (PackageManager.NameNotFoundException ignored) {}
        }
        return null;
    }

    /**
     * dp转px
     */
    static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * sp转px
     */
    static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }


    /**
     * 检查TextView的任意方向图标是否有不为空的
     */
    static boolean hasCompoundDrawables(TextView view) {
        Drawable[] drawables = view.getCompoundDrawables();
        if (drawables != null) {
            for (Drawable drawable : drawables) {
                if (drawable != null) {
                    return true;
                }
            }
        }
        return false;
    }
}