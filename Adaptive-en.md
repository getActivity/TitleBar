# About Attribute Adaptation Issues

* Hello, if you are new to this library, you can ignore this. If you have used the `TitleBar` library before, that is, versions below `9.0`, you need to adapt after upgrading to version `9.0`, otherwise `Android Studio` will report a `compilation failed` error. I apologize for this issue. The xml attribute naming in lower versions was not very standard. Now, optimizations have been made in versions `5.0` and `9.0`. Although the cost of this change is relatively high, I will do it without hesitation. If you use `TitleBar` but do not want to adapt, please do not upgrade the library version.

#### Adaptation Plan for Upgrading from 10.5 to 10.6

* Remove the `ripple` attribute value in `barStyle`, please use `app:barStyle="transparent"` and `style="@style/TitleBarRippleClickStyle"` to replace it.

#### Adaptation Plan for Upgrading from 10.2+ to 10.3

* Rename the `app:titleTextEllipsize` attribute, please use `app:titleOverflowMode` instead (in higher versions, CollapsingToolbarLayout occupies the titleTextEllipsize attribute).

* Rename the `app:leftTitleTextEllipsize` attribute, please use `app:leftTitleOverflowMode` instead.

* Rename the `app:rightTitleTextEllipsize` attribute, please use `app:rightTitleOverflowMode` instead.

#### Adaptation Plan for Upgrading from 9.3+ to 9.5

* Rename the `app:childPaddingVertical` attribute, please use `app:childVerticalPadding` instead.

* Split the `android:childPaddingHorizontal` attribute. The original attribute is split into the following attributes:

```text
app:titleHorizontalPadding

app:leftHorizontalPadding

app:rightHorizontalPadding
```

#### Adaptation Plan for Upgrading from 5.0+ to 9.0

* Remove the `app:backButton` attribute, please use `app:leftIcon` instead. Global replacement as follows:

```text
// Usage in old version
app:backButton="false"
// Usage in new version
app:leftIcon="@null"
```

* Split the `android:drawablePadding` attribute. The original attribute is split into the following attributes:

```text
app:titleIconPadding

app:leftIconPadding

app:rightIconPadding
```

* Split the `android:drawableSize` attribute. The original attribute is split into the following attributes:

```text
app:titleIconWidth
app:titleIconHeight

app:leftIconWidth
app:leftIconHeight

app:rightIconWidth
app:rightIconHeight
```

* Attribute renaming overview, just simple renaming, not much modification, you can use global replacement:

|       Version 5.0     |       Version 9.0     |
| :------------: | :-------------: |
|    `app:leftSize`   |    `app:leftTitleSize`   |
|    `app:rightSize`   |    `app:rightTitleSize`   |
|    `app:leftColor`   |    `app:leftTitleColor`   |
|    `app:rightColor`   |    `app:rightTitleColor`   |
|    `app:leftTint`   |    `app:leftIconTint`   |
|    `app:rightTint`   |    `app:rightIconTint`   |
|    `app:lineColor`   |    `app:lineDrawable`   |

* Both namespace and attributes have been changed (previously used system attributes). It is not recommended to use global replacement here, but to check one by one in the code:

|       Version 5.0     |       Version 9.0     |
| :------------: | :-------------: |
|    `android:paddingVertical`   |    `app:childPaddingVertical`   |
|    `android:paddingHorizontal`   |    `app:childPaddingHorizontal`   |

* Added text style settings for left and right titles (previous versions only had center title):

```text
app:leftTitleStyle
app:rightTitleStyle
```

* Added center title icon settings (previous versions only had left and right title icons):

```text
app:titleIcon
app:titleIconPadding
app:titleIconTint
```

* Added icon gravity settings (where the icon is drawn relative to the text):

```text
app:titleIconGravity
app:leftIconGravity
app:rightIconGravity
```

#### Adaptation Plan for Upgrading from 1.5+ to 5.0

* Regular adaptation: reverse single words, no longer use underscore format, but use single word with capitalized first letter instead

| Version 3.5   |  Version 5.0   |
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

* Special adaptation: remove underscores and use single word with capitalized first letter instead

|   Version 3.5   |    Version 5.0  |
|  :-------:  |  :---------:  |
| bar_style    |   barStyle    |
| line_visible |   lineVisible |
| line_color   |   lineColor |
| line_size    |   lineSize |

* Extreme adaptation: naming is completely different from before

|   Version 3.5   |    Version 5.0  |
|  :-------:   |  :-------:  |
|  icon_back    |   backButton |

* No adaptation needed: this attribute has not changed

| Version 3.5 |    Version 5.0  |
| :-----:  |  :-------:  | 
|  title   |    title   |

#### XML Attribute Naming Preview for Different Versions

* Version 3.5 XML attribute naming

```xml
<declare-styleable name="TitleBar">
    <!-- Title bar style -->
    <attr name="bar_style">
        <enum name="light" value="0x10" />
        <enum name="night" value="0x20" />
        <enum name="transparent" value="0x30" />
    </attr>
    <!-- Title -->
    <attr name="title" format="string" />
    <attr name="title_left" format="string"/>
    <attr name="title_right" format="string" />
    <!-- Icon -->
    <attr name="icon_left" format="reference" />
    <attr name="icon_right" format="reference" />
    <!-- Back button, enabled by default -->
    <attr name="icon_back" format="boolean" />
    <!-- Text color -->
    <attr name="color_title" format="color" />
    <attr name="color_right" format="color" />
    <attr name="color_left" format="color" />
    <!-- Text size -->
    <attr name="size_title" format="dimension" />
    <attr name="size_right" format="dimension" />
    <attr name="size_left" format="dimension" />
    <!-- Button background -->
    <attr name="background_left" format="reference|color" />
    <attr name="background_right" format="reference|color" />
    <!-- Divider line -->
    <attr name="line_visible" format="boolean" />
    <attr name="line_color" format="color" />
    <attr name="line_size" format="dimension" />
</declare-styleable>
```

* Version 5.0 XML attribute naming

```xml
<declare-styleable name="TitleBar">
    <!-- Overall style -->
    <attr name="barStyle">
        <enum name="light" value="0x10" />
        <enum name="night" value="0x20" />
        <enum name="transparent" value="0x30" />
    </attr>
    <!-- Center -->
    <attr name="title" format="string" />
    <attr name="titleColor" format="color" />
    <attr name="titleSize" format="dimension" />
    <!-- Left -->
    <attr name="leftTitle" format="string"/>
    <attr name="leftIcon" format="reference" /><!-- leftIcon has higher priority than backButton -->
    <attr name="backButton" format="boolean" /><!-- Back button (enabled by default) -->
    <attr name="leftColor" format="color" />
    <attr name="leftSize" format="dimension" />
    <attr name="leftBackground" format="reference|color" />
    <!-- Right -->
    <attr name="rightTitle" format="string" />
    <attr name="rightIcon" format="reference" />
    <attr name="rightColor" format="color" />
    <attr name="rightSize" format="dimension" />
    <attr name="rightBackground" format="reference|color" />
    <!-- Divider line -->
    <attr name="lineVisible" format="boolean" />
    <attr name="lineColor" format="reference|color" />
    <attr name="lineSize" format="dimension" />
</declare-styleable>
```

* Version 9.0 XML attribute naming

```xml
<declare-styleable name="TitleBar">

    <!-- Title bar background -->
    <attr name="android:background" />

    <!-- Title bar style -->
    <attr name="barStyle">
        <enum name="light" value="0x10" />
        <enum name="night" value="0x20" />
        <enum name="transparent" value="0x30" />
    </attr>

    <!-- Control horizontal padding -->
    <attr name="childPaddingHorizontal" format="dimension" />
    <!-- Control vertical padding (can be used to adjust the adaptive height of the title bar) -->
    <attr name="childPaddingVertical" format="dimension" />

    <!-- Center title -->
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

    <!-- Left title -->
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

    <!-- Right title -->
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

    <!-- Divider line -->
    <attr name="lineVisible" format="boolean" />
    <attr name="lineDrawable" format="reference|color" />
    <attr name="lineSize" format="dimension" />

</declare-styleable>
```