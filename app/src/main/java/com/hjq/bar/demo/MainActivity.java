package com.hjq.bar.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.hjq.bar.style.TitleBarLightStyle;

public class MainActivity extends AppCompatActivity {

    private TitleBar mTitleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //在这里可以初始化样式
        TitleBar.initStyle(new TitleBarLightStyle());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTitleBar = (TitleBar) findViewById(R.id.tb_main_title_bar);
        mTitleBar.setOnTitleBarListener(new OnTitleBarListener() {

            @Override
            public void onLeftClick(View v) {
                Toast.makeText(MainActivity.this, "左项被点击", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTitleClick(View v) {
                Toast.makeText(MainActivity.this, "标题被点击", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightClick(View v) {
                Toast.makeText(MainActivity.this, "右项被点击", Toast.LENGTH_SHORT).show();
            }
        });
    }
}