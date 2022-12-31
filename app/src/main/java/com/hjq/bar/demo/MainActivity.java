package com.hjq.bar.demo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.hjq.toast.ToastUtils;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/TitleBar
 *    time   : 2018/08/17
 *    desc   : TitleBar 使用案例
 */
public final class MainActivity extends AppCompatActivity implements Runnable {

    private static final Handler HANDLER = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TitleBar titleBar = findViewById(R.id.tb_main_bar_click);
        titleBar.setOnTitleBarListener(new OnTitleBarListener() {

            @Override
            public void onLeftClick(TitleBar titleBar) {
                ToastUtils.show("左项 View 被点击");
            }

            @Override
            public void onTitleClick(TitleBar titleBar) {
                ToastUtils.show("中间 View 被点击");
            }

            @Override
            public void onRightClick(TitleBar titleBar) {
                ToastUtils.show("右项 View 被点击");
            }
        });

        HANDLER.postDelayed(this, 2000);
    }

    @Override
    public void run() {
        TitleBar titleBar = findViewById(R.id.tb_main_bar_long_title);
        titleBar.setLeftTitle("左边");
        titleBar.setTitle("我是很长很长很长很长很长很长很长很长的标题");
        titleBar.setRightTitle("右边");
    }

    @Override
    protected void onDestroy() {
        HANDLER.removeCallbacks(this);
        super.onDestroy();
    }
}