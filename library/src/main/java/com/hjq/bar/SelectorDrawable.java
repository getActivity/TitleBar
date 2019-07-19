package com.hjq.bar;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/TitleBar
 *    time   : 2019/03/10
 *    desc   : 状态选择器构建器
 */
public final class SelectorDrawable extends StateListDrawable {

    public final static class Builder {

        /** 默认状态 */
        private Drawable mDefault;
        /** 焦点状态 */
        private Drawable mFocused;
        /** 按下状态 */
        private Drawable mPressed;
        /** 选中状态 */
        private Drawable mChecked;
        /** 启用状态 */
        private Drawable mEnabled;
        /** 选择状态 */
        private Drawable mSelected;
        /** 光标悬浮状态（4.0新特性） */
        private Drawable mHovered;

        public Builder setDefault(Drawable drawable) {
            mDefault = drawable;
            return this;
        }

        public Builder setFocused(Drawable drawable) {
            mFocused = drawable;
            return this;
        }

        public Builder setPressed(Drawable drawable) {
            mPressed = drawable;
            return this;
        }

        public Builder setChecked(Drawable drawable) {
            mChecked = drawable;
            return this;
        }

        public Builder setEnabled(Drawable drawable) {
            mEnabled = drawable;
            return this;
        }

        public Builder setSelected(Drawable drawable) {
            mSelected = drawable;
            return this;
        }

        public Builder setHovered(Drawable drawable) {
            mHovered = drawable;
            return this;
        }

        public SelectorDrawable builder() {
            SelectorDrawable selector = new SelectorDrawable();
            if (mPressed != null) {
                selector.addState(new int[]{android.R.attr.state_pressed}, mPressed);
            }
            if (mFocused != null) {
                selector.addState(new int[]{android.R.attr.state_focused}, mFocused);
            }
            if (mChecked != null) {
                selector.addState(new int[]{android.R.attr.state_checked}, mChecked);
            }
            if (mEnabled != null) {
                selector.addState(new int[]{android.R.attr.state_enabled}, mEnabled);
            }
            if (mSelected != null) {
                selector.addState(new int[]{android.R.attr.state_selected}, mSelected);
            }
            if (mHovered != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                    selector.addState(new int[]{android.R.attr.state_hovered}, mHovered);
                }
            }
            if (mDefault != null) {
                selector.addState(new int[]{}, mDefault);
            }
            return selector;
        }
    }
}