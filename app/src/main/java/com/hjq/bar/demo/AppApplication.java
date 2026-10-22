package com.hjq.bar.demo;

import android.app.Application;
import android.content.Context;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;

import com.hjq.bar.TitleBar;
import com.hjq.bar.style.LightBarStyle;
import com.hjq.toast.Toaster;

public final class AppApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // 初始化 Toast
        Toaster.init(this);

        // 设置标题栏全局样式
        TitleBar.setGlobalStyle(new LightBarStyle() {

            @NonNull
            @Override
            public TextView newTitleView(@NonNull Context context) {
                return new AppCompatTextView(context);
            }

            @NonNull
            @Override
            public TextView newLeftView(@NonNull Context context) {
                return new AppCompatTextView(context);
            }

            @NonNull
            @Override
            public TextView newRightView(@NonNull Context context) {
                return new AppCompatTextView(context);
            }
        });
    }
}