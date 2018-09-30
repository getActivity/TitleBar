package com.hjq.bar.style;

import com.hjq.bar.R;

/**
 *    author : HJQ
 *    github : https://github.com/getActivity/TitleBar
 *    time   : 2018/08/20
 *    desc   : 默认透明主题样式实现
 */
public class TitleBarTransparentStyle extends TitleBarNightStyle {

    @Override
    public int getBackgroundColor() {
        return 0x00000000;
    }

    @Override
    public int getLeftViewColor() {
        return 0xFFFFFFFF;
    }

    @Override
    public int getTitleViewColor() {
        return 0xFFFFFFFF;
    }

    @Override
    public int getRightViewColor() {
        return 0xFFFFFFFF;
    }

    @Override
    public boolean getLineVisibility() {
        return false;
    }

    @Override
    public int getLineBackgroundColor() {
        return 0x00000000;
    }

    @Override
    public int getLeftViewBackground() {
        return R.drawable.bar_selector_selectable_transparent;
    }

    @Override
    public int getRightViewBackground() {
        return R.drawable.bar_selector_selectable_transparent;
    }
}