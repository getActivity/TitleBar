package com.hjq.bar.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.hjq.bar.TitleBar;
import com.hjq.bar.demo.R;

public class MainActivity extends AppCompatActivity {

    private TitleBar mTitleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTitleBar = (TitleBar) findViewById(R.id.tb_main_title_bar);
        mTitleBar.setOnTitleBarListener(new TitleBar.OnTitleBarListener() {

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
