# 关于适配的问题

> 你好，如果你是刚使用这个库的人不必理会，如果你之前使用了 `TitleBar` 这个库，也就是 5.0 版本以下的，在升级到 `5.0` 版本后需要进行适配，否则 `Android Studio` 会报错`编译不通过`，对于这个问题我表示十分抱歉，低版本的 `xml` 属性命名得并不是很规范，现在在 `5.0` 版本给予了纠正，尽管这次的代价比较大，但是我会义无反顾去做，如果你使用了 `TitleBar` 但是不想进行适配，请不要`升级`依赖版本

#### 3.5 版本属性命名

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

#### 5.0 版本属性命名

    <declare-styleable name="TitleBar"  tools:ignore="ResourceName">
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

#### 适配方案

> 常规适配：单词反向，不再采用下划线形式，而用单词开头大写的方式代替

3.5 版本   |  5.0 版本
------------ | -------------
title_left | leftTitle
title_right | rightTitle
icon_left   | leftIcon
icon_right | rightIcon
color_title | titleColor
color_right | rightColor
color_left | leftColor
size_title | titleSize
size_right | rightSize
size_left | leftSize
background_left | leftBackground
background_right| rightBackground

> 特殊适配：去除下划线，并且用单词开头大写的方式代替

  3.5 版本   |    5.0 版本 
  -------    |  ------------- 
bar_style    |   barStyle   
line_visible |   lineVisible
line_color   |   lineColor
line_size    |   lineSize

> 极端适配：命名和之前完全不同

  3.5 版本   |    5.0 版本 
  -------    |  ------------- 
icon_back    |   backButton

> 无需适配：这个属性没有任何改动

3.5 版本 |    5.0 版本 
------  |  ------------- 
title   |     title

