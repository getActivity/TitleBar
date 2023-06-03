package com.hjq.bar.demo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.hjq.toast.Toaster;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/TitleBar
 *    time   : 2018/08/17
 *    desc   : TitleBar 使用案例
 */
public final class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TitleBar titleBar = findViewById(R.id.tb_main_bar_click);
        titleBar.setOnTitleBarListener(new OnTitleBarListener() {

            @Override
            public void onLeftClick(TitleBar titleBar) {
                Toaster.show("左项 View 被点击");
            }

            @Override
            public void onTitleClick(TitleBar titleBar) {
                Toaster.show("中间 View 被点击");
            }

            @Override
            public void onRightClick(TitleBar titleBar) {
                Toaster.show("右项 View 被点击");
            }
        });
    }
}