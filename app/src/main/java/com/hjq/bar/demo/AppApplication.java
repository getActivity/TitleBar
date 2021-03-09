package com.hjq.bar.demo;

import android.app.Application;
import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.widget.TextView;

import com.hjq.bar.TitleBar;
import com.hjq.bar.initializer.LightBarInitializer;
import com.hjq.toast.ToastUtils;

public final class AppApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // 初始化 Toast
        ToastUtils.init(this);

        // 初始化 TitleBar
        TitleBar.setDefaultInitializer(new LightBarInitializer() {
            @Override
            protected TextView createTextView(Context context) {
                return new AppCompatTextView(context);
            }
        });
    }
}