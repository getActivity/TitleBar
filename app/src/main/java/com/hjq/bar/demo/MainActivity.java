package com.hjq.bar.demo;

import android.graphics.Insets;
import android.os.Build;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnApplyWindowInsetsListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.WindowInsets;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
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

        TitleBar clickTitlBar = findViewById(R.id.tb_main_bar_click);
        clickTitlBar.setOnTitleBarListener(new OnTitleBarListener() {

            @Override
            public void onLeftClick(@NonNull TitleBar titleBar) {
                Toaster.show(R.string.demo_left_view_clicked);
            }

            @Override
            public void onTitleClick(@NonNull TitleBar titleBar) {
                Toaster.show(R.string.demo_center_view_clicked);
            }

            @Override
            public void onRightClick(@NonNull TitleBar titleBar) {
                Toaster.show(R.string.demo_right_view_clicked);
            }
        });

        Toolbar toolbar = findViewById(R.id.tb_main_bar_tool);
        TitleBar titleBar = findViewById(R.id.tb_main_bar_title);

        // 适配 Android 15 EdgeToEdge 特性
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM) {
            getWindow().getDecorView().setOnApplyWindowInsetsListener(new OnApplyWindowInsetsListener()  {

                @NonNull
                @Override
                public WindowInsets onApplyWindowInsets(@NonNull View v, @NonNull WindowInsets insets) {
                    Insets systemBars = insets.getInsets(WindowInsets.Type.systemBars());
                    // v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                    LayoutParams toolbarLayoutParams = toolbar.getLayoutParams();
                    if (toolbarLayoutParams instanceof MarginLayoutParams) {
                        MarginLayoutParams toolbarMarginLayoutParams = (MarginLayoutParams) toolbarLayoutParams;
                        toolbarMarginLayoutParams.setMargins(toolbarMarginLayoutParams.leftMargin, systemBars.top,
                                                             toolbarMarginLayoutParams.rightMargin, toolbarMarginLayoutParams.bottomMargin);
                        toolbar.setLayoutParams(toolbarMarginLayoutParams);
                    }

                    LayoutParams titleBarLayoutParams = titleBar.getLayoutParams();
                    if (titleBarLayoutParams instanceof MarginLayoutParams) {
                        MarginLayoutParams titleBarMarginLayoutParams = (MarginLayoutParams) titleBarLayoutParams;
                        titleBarMarginLayoutParams.setMargins(titleBarMarginLayoutParams.leftMargin, systemBars.top,
                                                              titleBarMarginLayoutParams.rightMargin, titleBarMarginLayoutParams.bottomMargin);
                        titleBar.setLayoutParams(titleBarMarginLayoutParams);
                    }
                    return insets;
                }
            });
        }
    }
}