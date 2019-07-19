package com.hjq.bar.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.hjq.toast.ToastUtils;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/TitleBar
 *    time   : 2018/08/17
 *    desc   : TitleBar 使用案例
 */
public class MainActivity extends AppCompatActivity {

    private TitleBar mTitleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTitleBar = (TitleBar) findViewById(R.id.tb_main_title_bar);
        mTitleBar.setOnTitleBarListener(new OnTitleBarListener() {

            @Override
            public void onLeftClick(View v) {
                ToastUtils.show("左项 View 被点击");
            }

            @Override
            public void onTitleClick(View v) {
                ToastUtils.show("中间 View 被点击");
            }

            @Override
            public void onRightClick(View v) {
                ToastUtils.show("右项 View 被点击");
            }
        });
    }
}