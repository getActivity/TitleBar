package com.hjq.bar.demo;

import android.app.Application;

import com.hjq.bar.TitleBar;
import com.hjq.bar.style.TitleBarLightStyle;
import com.hjq.toast.ToastUtils;

public class TitleBarApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化 Toast
        ToastUtils.init(this);

        // 初始化 TitleBar 样式
        TitleBar.initStyle(new TitleBarLightStyle(this));
    }
}
