package com.hjq.bar;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import androidx.annotation.Nullable;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/TitleBar
 *    time   : 2019/03/10
 *    desc   : 状态选择器构建器
 */
@SuppressWarnings("unused")
public final class SelectorDrawable extends StateListDrawable {

    public final static class Builder {

        /** 默认状态 */
        @Nullable
        private Drawable mDefault;
        /** 焦点状态 */
        @Nullable
        private Drawable mFocused;
        /** 按下状态 */
        @Nullable
        private Drawable mPressed;
        /** 选中状态 */
        @Nullable
        private Drawable mChecked;
        /** 选择状态 */
        @Nullable
        private Drawable mSelected;
        /** 启用状态 */
        @Nullable
        private Drawable mEnabled;
        /** 光标悬浮状态（4.0新特性） */
        @Nullable
        private Drawable mHovered;

        public Builder setDefault(@Nullable Drawable drawable) {
            mDefault = drawable;
            return this;
        }

        public Builder setFocused(@Nullable Drawable drawable) {
            mFocused = drawable;
            return this;
        }

        public Builder setPressed(@Nullable Drawable drawable) {
            mPressed = drawable;
            return this;
        }

        public Builder setChecked(@Nullable Drawable drawable) {
            mChecked = drawable;
            return this;
        }

        public Builder setSelected(@Nullable Drawable drawable) {
            mSelected = drawable;
            return this;
        }

        public Builder setEnabled(@Nullable Drawable drawable) {
            mEnabled = drawable;
            return this;
        }

        public Builder setHovered(@Nullable Drawable drawable) {
            mHovered = drawable;
            return this;
        }

        public SelectorDrawable build() {
            SelectorDrawable selector = new SelectorDrawable();
            if (mPressed != null) {
                selector.addState(new int[]{ android.R.attr.state_pressed }, mPressed);
            }
            if (mFocused != null) {
                selector.addState(new int[]{ android.R.attr.state_focused }, mFocused);
            }
            if (mChecked != null) {
                selector.addState(new int[]{ android.R.attr.state_checked }, mChecked);
            }
            if (mSelected != null) {
                selector.addState(new int[]{ android.R.attr.state_selected }, mSelected);
            }
            if (mEnabled != null) {
                selector.addState(new int[]{ android.R.attr.state_enabled }, mEnabled);
            }
            if (mHovered != null) {
                selector.addState(new int[]{ android.R.attr.state_hovered }, mHovered);
            }
            if (mDefault != null) {
                selector.addState(new int[]{}, mDefault);
            }
            return selector;
        }
    }
}