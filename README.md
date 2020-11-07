# 标题栏

> 码云地址：[Gitee](https://gitee.com/getActivity/TitleBar)

> 博客地址：[Android标题栏（TitleBar）绝佳解决方案](https://www.jianshu.com/p/617be02dc265)

> 已投入公司项目多时，没有任何毛病，可胜任任何需求，[点击此处下载Demo](TitleBar.apk)

> 想了解实现原理的可以参考文章：[纯手工打造一个通用的标题栏 TitleBar](https://www.jianshu.com/p/ccf6506335e7)

![](TitleBar.gif)

## 5.0 版本、5.0 版本、5.0 版本 XML 属性已经改头换面了，请尽快进行[适配](Adaptive.md)

#### 集成步骤

```groovy
dependencies {
    // 标题栏框架：https://github.com/getActivity/TitleBar
    implementation 'com.hjq:titlebar:8.2'
}
```

#### 属性大全，[如何适配旧版本的属性？](Adaptive.md)

```xml
<declare-styleable name="TitleBar">
    <!-- 整体样式 -->
    <attr name="barStyle">
        <enum name="light" value="0x10" />
        <enum name="night" value="0x20" />
        <enum name="transparent" value="0x30" />
        <enum name="ripple" value="0x40" />
    </attr>
    <!-- 中间 -->
    <attr name="title" format="string" />
    <attr name="titleColor" format="color" />
    <attr name="titleSize" format="dimension" />
    <attr name="titleGravity">
        <flag name="top" value="0x30" />
        <flag name="bottom" value="0x50" />
        <flag name="left" value="0x03" />
        <flag name="right" value="0x05" />
        <flag name="center_vertical" value="0x10" />
        <flag name="center_horizontal" value="0x01" />
        <flag name="center" value="0x11" />
        <flag name="start" value="0x00800003" />
        <flag name="end" value="0x00800005" />
    </attr>
    <attr name="titleStyle">
        <flag name="normal" value="0" />
        <flag name="bold" value="1" />
        <flag name="italic" value="2" />
    </attr>
    <!-- 左边 -->
    <attr name="leftTitle" format="string"/>
    <!-- leftIcon 优先于 backButton -->
    <attr name="leftIcon" format="reference" />
    <attr name="leftTint" format="color" />
    <!-- 返回按钮（默认开） -->
    <attr name="backButton" format="boolean" />
    <attr name="leftColor" format="color" />
    <attr name="leftSize" format="dimension" />
    <attr name="leftBackground" format="reference|color" />
    <!-- 右边 -->
    <attr name="rightTitle" format="string" />
    <attr name="rightIcon" format="reference" />
    <attr name="rightTint" format="color" />
    <attr name="rightColor" format="color" />
    <attr name="rightSize" format="dimension" />
    <attr name="rightBackground" format="reference|color" />
    <!-- 分割线 -->
    <attr name="lineVisible" format="boolean" />
    <attr name="lineColor" format="reference|color" />
    <attr name="lineSize" format="dimension" />
    <!-- 图标显示大小 -->
    <attr name="drawableSize" format="dimension" />
    <!-- 图标和文字的间距 -->
    <attr name="android:drawablePadding" />
    <!-- 控件水平内间距 -->
    <attr name="android:paddingVertical" />
    <!-- 控件垂直内间距（可用于调整标题栏自适应的高度） -->
    <attr name="android:paddingHorizontal" />
</declare-styleable>
```

#### XML示例

> [点我查看完整的Demo示例](app/src/main/res/layout/activity_main.xml)

```xml
<com.hjq.bar.TitleBar
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:title="默认的标题栏" />
```

#### 设置监听事件

```java
mTitleBar.setOnTitleBarListener(new OnTitleBarListener() {

    @Override
    public void onLeftClick(View v) {
        ToastUtils.show("左项View被点击");
    }

    @Override
    public void onTitleClick(View v) {
        ToastUtils.show("中间View被点击");
    }

    @Override
    public void onRightClick(View v) {
        ToastUtils.show("右项View被点击");
    }
});
```

#### 统一 TitleBar 样式

> 如果对 TitleBar 的默认样式或者属性不满意，可以在 Application 初始化的地方进行拦截并修改

```java
public class XXApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // 初始化 TitleBar
        TitleBar.setDefaultInitializer(new LightBarInitializer() {
            @Override
            public TextView getLeftView(Context context) {
                return super.getLeftView(context);
            }

            @Override
            public TextView getTitleView(Context context) {
                return super.getTitleView(context);
            }

            @Override
            public TextView getRightView(Context context) {
                return super.getRightView(context);
            }

            @Override
            public View getLineView(Context context) {
                return super.getLineView(context);
            }

            @Override
            public Drawable getBackgroundDrawable(Context context) {
                return super.getBackgroundDrawable(context);
            }

            @Override
            public Drawable getBackIcon(Context context) {
                return super.getBackIcon(context);
            }

            @Override
            public int getHorizontalPadding(Context context) {
                return super.getHorizontalPadding(context);
            }

            @Override
            public int getVerticalPadding(Context context) {
                return super.getVerticalPadding(context);
            }
        });
    }
}
```

#### 框架亮点

* 性能最佳：不使用 LayoutInflater，而使用代码创建 View 的形式

* 体验最优：TitleBar 默认样式效果已经非常好，可下载 Demo 测试

* 支持操控子控件：可以在代码中获取 TitleBar 的子控件进行调用相关的 API

* 兼容沉浸式状态栏：兼容 Github 的沉浸式状态栏框架，达到完全沉浸的效果

* 框架兼容性良好：本框架不依赖任何第三方库，支持兼容所有的安卓版本

* 支持全局配置样式：可以在 Application 中初始化 TitleBar 样式，达到一劳永逸的效果

#### 作者的其他开源项目

* 安卓技术中台：[AndroidProject](https://github.com/getActivity/AndroidProject)

* 网络框架：[EasyHttp](https://github.com/getActivity/EasyHttp)

* 日志框架：[Logcat](https://github.com/getActivity/Logcat)

* 权限框架：[XXPermissions](https://github.com/getActivity/XXPermissions)

* 吐司框架：[ToastUtils](https://github.com/getActivity/ToastUtils)

* 国际化框架：[MultiLanguages](https://github.com/getActivity/MultiLanguages)

* 悬浮窗框架：[XToast](https://github.com/getActivity/XToast)

#### Android技术讨论Q群：78797078

#### 如果您觉得我的开源库帮你节省了大量的开发时间，请扫描下方的二维码随意打赏，要是能打赏个 10.24 :monkey_face:就太:thumbsup:了。您的支持将鼓励我继续创作:octocat:

![](https://raw.githubusercontent.com/getActivity/Donate/master/picture/pay_ali.png) ![](https://raw.githubusercontent.com/getActivity/Donate/master/picture/pay_wechat.png)

#### [点击查看捐赠列表](https://github.com/getActivity/Donate)

## License

```text
Copyright 2018 Huang JinQun

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
