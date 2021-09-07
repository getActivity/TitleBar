# 关于属性适配的问题

* 你好，如果你是刚使用这个库的人可以不必理会，如果你之前使用了 `TitleBar` 这个库，也就是 `9.0` 版本以下的，在升级到 `9.0` 版本后需要进行适配，否则 `Android Studio` 会报错`编译不通过`，对于这个问题我表示十分抱歉，低版本的 `xml` 属性命名得并不是很规范，现在在 `5.0` 和 `9.0` 版本进行优化，尽管这次的代价比较大，但是我会义无反顾去做，如果你使用了 `TitleBar` 但是不想进行适配，请不要`升级`依赖库版本。

#### 从 5.0 以上的版本升级到 9.0 版本适配方案

* 删除 `app:backButton` 属性，请使用 `app:leftIcon` 代替，全局替换如下：

```text
// 旧版本的用法
app:backButton="false"
// 新版本的用法
app:leftIcon="@null"
```

* 拆分 `android:drawablePadding` 属性，将原有属性拆分为以下几个属性：

```text
app:titleIconPadding

app:leftIconPadding

app:rightIconPadding
```

* 拆分 `android:drawableSize` 属性，将原有属性拆分为以下几个属性：

```text
app:titleIconWidth
app:titleIconHeight

app:leftIconWidth
app:leftIconHeight

app:rightIconWidth
app:rightIconHeight
```

* 属性改名一览，只是简单改名，没有做太多修改，大家可以使用全局替换：

|       5.0 版本     |       9.0 版本     |
| :------------: | :-------------: |
|    `app:leftSize`   |    `app:leftTitleSize`   |
|    `app:rightSize`   |    `app:rightTitleSize`   |
|    `app:leftColor`   |    `app:leftTitleColor`   |
|    `app:rightColor`   |    `app:rightTitleColor`   |
|    `app:leftTint`   |    `app:leftIconTint`   |
|    `app:rightTint`   |    `app:rightIconTint`   |
|    `app:lineColor`   |    `app:lineDrawable`   |

* 命名空间和属性都修改（之前用的是系统属性），在这里不推荐大家使用全局替换，而是在代码中一一检查：

|       5.0 版本     |       9.0 版本     |
| :------------: | :-------------: |
|    `android:paddingVertical`   |    `app:childPaddingVertical`   |
|    `android:paddingHorizontal`   |    `app:childPaddingHorizontal`   |

* 新增左标提和右标题的文本样式设置（之前的版本只有中间标题的）：

```text
app:leftTitleStyle
app:rightTitleStyle
```

* 新增中间标题图标设置（之前的版本只有左标题和右标题才有）：

```text
app:titleIcon
app:titleIconPadding
app:titleIconTint
```

* 新增图标的显示重心设置（图标在文字的哪个位置上面绘制）：

```text
app:titleIconGravity
app:leftIconGravity
app:rightIconGravity
```

#### 从 1.5 以上的版本升级到 5.0 版本适配方案

* 常规适配：单词反向，不再采用下划线形式，而用单词开头大写的方式代替

| 3.5 版本   |  5.0 版本   |
| :------------: | :-------------: |
| title_left | leftTitle |
| title_right | rightTitle |
| icon_left   | leftIcon |
| icon_right | rightIcon |
| color_title | titleColor |
| color_right | rightColor |
| color_left | leftColor |
| size_title | titleSize |
| size_right | rightSize |
| size_left | leftSize |
| background_left | leftBackground |
| background_right| rightBackground |

* 特殊适配：去除下划线，并且用单词开头大写的方式代替

|   3.5 版本   |    5.0 版本  |
|  :-------:  |  :---------:  |
| bar_style    |   barStyle    |
| line_visible |   lineVisible |
| line_color   |   lineColor |
| line_size    |   lineSize |

* 极端适配：命名和之前完全不同

|   3.5 版本   |    5.0 版本  |
|  :-------:   |  :-------:  |
|  icon_back    |   backButton |

* 无需适配：这个属性没有任何改动

| 3.5 版本 |    5.0 版本  |
| :-----:  |  :-------:  | 
|  title   |    title   |

#### 不同版本的 XML 属性命名预览

* 3.5 版本 XML 属性命名

```xml
<declare-styleable name="TitleBar">
    <!-- 标题栏的样式 -->
    <attr name="bar_style">
        <enum name="light" value="0x10" />
        <enum name="night" value="0x20" />
        <enum name="transparent" value="0x30" />
    </attr>
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
    <attr name="line_visible" format="boolean" />
    <attr name="line_color" format="color" />
    <attr name="line_size" format="dimension" />
</declare-styleable>
```

* 5.0 版本 XML 属性命名

```xml
<declare-styleable name="TitleBar">
    <!-- 整体样式 -->
    <attr name="barStyle">
        <enum name="light" value="0x10" />
        <enum name="night" value="0x20" />
        <enum name="transparent" value="0x30" />
    </attr>
    <!-- 中间 -->
    <attr name="title" format="string" />
    <attr name="titleColor" format="color" />
    <attr name="titleSize" format="dimension" />
    <!-- 左边 -->
    <attr name="leftTitle" format="string"/>
    <attr name="leftIcon" format="reference" /><!-- leftIcon 优先于 backButton -->
    <attr name="backButton" format="boolean" /><!-- 返回按钮（默认开） -->
    <attr name="leftColor" format="color" />
    <attr name="leftSize" format="dimension" />
    <attr name="leftBackground" format="reference|color" />
    <!-- 右边 -->
    <attr name="rightTitle" format="string" />
    <attr name="rightIcon" format="reference" />
    <attr name="rightColor" format="color" />
    <attr name="rightSize" format="dimension" />
    <attr name="rightBackground" format="reference|color" />
    <!-- 分割线 -->
    <attr name="lineVisible" format="boolean" />
    <attr name="lineColor" format="reference|color" />
    <attr name="lineSize" format="dimension" />
</declare-styleable>
```

* 9.0 版本 XML 属性命名

```xml
<declare-styleable name="TitleBar">

    <!-- 标题栏背景 -->
    <attr name="android:background" />

    <!-- 标题栏样式 -->
    <attr name="barStyle">
        <enum name="light" value="0x10" />
        <enum name="night" value="0x20" />
        <enum name="transparent" value="0x30" />
        <enum name="ripple" value="0x40" />
    </attr>

    <!-- 控件水平内间距 -->
    <attr name="childPaddingHorizontal" format="dimension" />
    <!-- 控件垂直内间距（可用于调整标题栏自适应的高度） -->
    <attr name="childPaddingVertical" format="dimension" />

    <!-- 中间标题 -->
    <attr name="title" format="string" />
    <attr name="titleColor" format="color" />
    <attr name="titleSize" format="dimension" />
    <attr name="titleGravity">
        <flag name="left" value="0x03" />
        <flag name="right" value="0x05" />
        <flag name="center" value="0x11" />
        <flag name="start" value="0x00800003" />
        <flag name="end" value="0x00800005" />
    </attr>
    <attr name="titleStyle">
        <flag name="normal" value="0" />
        <flag name="bold" value="1" />
        <flag name="italic" value="2" />
    </attr>
    <attr name="titleIcon" format="reference" />
    <attr name="titleIconWidth" format="dimension" />
    <attr name="titleIconHeight" format="dimension" />
    <attr name="titleIconPadding" format="dimension" />
    <attr name="titleIconTint" format="color" />
    <attr name="titleIconGravity">
        <flag name="top" value="0x30" />
        <flag name="bottom" value="0x50" />
        <flag name="left" value="0x03" />
        <flag name="right" value="0x05" />
        <flag name="start" value="0x00800003" />
        <flag name="end" value="0x00800005" />
    </attr>

    <!-- 左边标题 -->
    <attr name="leftTitle" format="string"/>
    <attr name="leftTitleColor" format="color" />
    <attr name="leftTitleSize" format="dimension" />
    <attr name="leftTitleStyle">
        <flag name="normal" value="0" />
        <flag name="bold" value="1" />
        <flag name="italic" value="2" />
    </attr>
    <attr name="leftIcon" format="reference" />
    <attr name="leftIconWidth" format="dimension" />
    <attr name="leftIconHeight" format="dimension" />
    <attr name="leftIconPadding" format="dimension" />
    <attr name="leftIconTint" format="color" />
    <attr name="leftIconGravity">
        <flag name="top" value="0x30" />
        <flag name="bottom" value="0x50" />
        <flag name="left" value="0x03" />
        <flag name="right" value="0x05" />
        <flag name="start" value="0x00800003" />
        <flag name="end" value="0x00800005" />
    </attr>
    <attr name="leftBackground" format="reference|color" />

    <!-- 右边标题 -->
    <attr name="rightTitle" format="string" />
    <attr name="rightTitleColor" format="color" />
    <attr name="rightTitleSize" format="dimension" />
    <attr name="rightTitleStyle">
        <flag name="normal" value="0" />
        <flag name="bold" value="1" />
        <flag name="italic" value="2" />
    </attr>
    <attr name="rightIcon" format="reference" />
    <attr name="rightIconWidth" format="dimension" />
    <attr name="rightIconHeight" format="dimension" />
    <attr name="rightIconPadding" format="dimension" />
    <attr name="rightIconTint" format="color" />
    <attr name="rightIconGravity">
        <flag name="top" value="0x30" />
        <flag name="bottom" value="0x50" />
        <flag name="left" value="0x03" />
        <flag name="right" value="0x05" />
        <flag name="start" value="0x00800003" />
        <flag name="end" value="0x00800005" />
    </attr>
    <attr name="rightBackground" format="reference|color" />

    <!-- 分割线 -->
    <attr name="lineVisible" format="boolean" />
    <attr name="lineDrawable" format="reference|color" />
    <attr name="lineSize" format="dimension" />

</declare-styleable>
```



