# 标题栏

> 博客地址：[Android标题栏（TitleBar）绝佳解决方案](https://www.jianshu.com/p/617be02dc265)

> 已投入公司项目多时，没有任何毛病，可胜任任何需求，[点击此处下载Demo](https://raw.githubusercontent.com/getActivity/TitleBar/master/TitleBar.apk)

> 想了解实现原理的可以参考文章：[纯手工打造一个通用的标题栏TitleBar](https://www.jianshu.com/p/ccf6506335e7)

![](TitleBar.png)

![](TitleBar.jpg)

#### 集成步骤

    dependencies {
        implementation 'com.hjq:titlebar:3.0'
    }

#### 属性大全（划重点，要考）

    <declare-styleable name="TitleBar">
        <!-- 标题 -->
        <attr name="title" format="string" />
        <attr name="title_left" format="string"/>
        <attr name="title_right" format="string" />
        <!-- 图标 -->
        <attr name="icon_left" format="reference" />
        <attr name="icon_right" format="reference" />
        <!-- 返回按钮，默认开 -->
        <attr name="icon_back" format="boolean" />
        <!-- 文字颜色 -->
        <attr name="color_title" format="color" />
        <attr name="color_right" format="color" />
        <attr name="color_left" format="color" />
        <!-- 文字大小 -->
        <attr name="size_title" format="dimension" />
        <attr name="size_right" format="dimension" />
        <attr name="size_left" format="dimension" />
        <!-- 按钮背景 -->
        <attr name="background_left" format="reference|color" />
        <attr name="background_right" format="reference|color" />
        <!-- 分割线 -->
        <attr name="line" format="boolean" />
        <attr name="color_line" format="color" />
    </declare-styleable>

#### XML示例

> [点我查看完整的Demo示例](https://github.com/getActivity/TitleBar/blob/master/app/src/main/res/layout/activity_main.xml)

    <com.hjq.bar.TitleBar
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:title="默认的标题栏" />

#### 设置监听事件

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

#### 统一TitleBar样式

> 如果对TitleBar的默认样式不满意，可以在Application初始化样式，具体可参考[TitleBarLightStyle](https://github.com/getActivity/TitleBar/blob/master/library/src/main/java/com/hjq/bar/TitleBarLightStyle.java)或者[TitleBarNightStyle](https://github.com/getActivity/TitleBar/blob/master/library/src/main/java/com/hjq/bar/TitleBarNightStyle.java)类的实现

	public class XXApplication extends Application {
	
	    @Override
	    public void onCreate() {
	        super.onCreate();
	        TitleBar.initStyle(new ITitleBarStyle());
	    }
	}

#### 框架亮点

* 性能最佳：不使用LayoutInflater，而使用代码创建View的形式

* 体验最优：TitleBar默认样式效果已经非常好，可下载Demo测试

* 支持夜间模式：可以直接引用框架中的TitleBarNightStyle样式

* 支持操控子控件：可以在代码中获取TitleBar的子控件进行调用相关的API

* 支持自定义布局：可将TitleBar当做FrameLayout使用，直接在XML中自定义布局

* 兼容沉浸式状态栏：兼容Github的沉浸式状态栏框架，达到完全沉浸的效果

* 框架兼容性良好：本框架不依赖任何第三方库，支持兼容所有的安卓版本

* 支持全局配置样式：可以在Application中初始化TitleBar样式，达到一劳永逸的效果

#### Android技术讨论Q群：78797078

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
