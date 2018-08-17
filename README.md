# 标题栏

>[点击此处下载Demo](https://raw.githubusercontent.com/getActivity/TitleBar/master/TitleBar.apk)，博客地址：[纯手工打造一个通用的标题栏TitleBar](https://www.jianshu.com/p/ccf6506335e7)

![](TitleBar.png)

![](TitleBar.jpg)

#### 集成步骤

    dependencies {
        implementation 'com.hjq:titlebar:1.5'
    }

####XML示例

    <com.hjq.bar.TitleBar
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="60dp"
        app:title="默认的标题栏" />

    <com.hjq.bar.TitleBar
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="20dp"
        app:icon_back="false"
        app:title="不带箭头的标题栏" />

    <com.hjq.bar.TitleBar
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="20dp"
        app:line="false"
        app:title="不带分割线的标题栏" />

    <com.hjq.bar.TitleBar
        android:layout_width="match_parent"
        android:layout_height="50dp"
        android:layout_marginTop="20dp"
        app:title="自定义高度的标题栏" />

    <com.hjq.bar.TitleBar
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="20dp"
        app:icon_left="@mipmap/ic_launcher"
        app:icon_right="@mipmap/ic_launcher"
        app:title="带图标的标题栏" />

    <com.hjq.bar.TitleBar
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="20dp"
        app:icon_back="false"
        app:title="带文本的标题栏\n12345"
        app:title_left="左边"
        app:title_right="右边" />

    <com.hjq.bar.TitleBar
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="20dp"
        app:color_left="#FF3300"
        app:color_right="#0033FF"
        app:icon_back="false"
        app:size_left="18sp"
        app:size_right="18sp"
        app:size_title="20sp"
        app:title="文字大小和颜色"
        app:title_left="左边"
        app:title_right="右边" />

    <com.hjq.bar.TitleBar
        android:id="@+id/tb_main_title_bar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="20dp"
        app:icon_left="@mipmap/ic_launcher"
        app:icon_right="@mipmap/ic_launcher"
        app:title="监听事件的标题栏"
        app:title_left="左边"
        app:title_right="右边" />

    <com.hjq.bar.TitleBar
        style="@style/TitleBarNightStyle"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="20dp"
        app:title="夜间模式的标题栏" />

    <com.hjq.bar.TitleBar
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="20dp"
        app:icon_back="false">

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="match_parent"
            android:layout_gravity="center"
            android:drawableLeft="@mipmap/ic_launcher"
            android:gravity="center"
            android:text="TitleBar可以当做FrameLayout使用\n在这里也可以添加自定义布局" />

    </com.hjq.bar.TitleBar>

#### 设置点击监听

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

## License

```text
Copyright 2018 Huang Jinqun

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
