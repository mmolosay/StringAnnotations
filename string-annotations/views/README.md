[![](https://jitpack.io/v/mmolosay/stringannotations.svg)](https://jitpack.io/#mmolosay/stringannotations)
[![License](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](https://www.apache.org/licenses/LICENSE-2.0)

### :string-annotations:views

Artifact of **StringAnnotations** library for `Android Views` UI.

Configuration
=======
General configuration is described in [repository's README](https://github.com/mmolosay/StringAnnotations#general-configuration).

ClickableTextAppearance
-----
You should define default appearance of your [Clickable](#clickable) annotations text.

1. (optional) Define your own style for clickable text appearance. You can use [Base.ClickableTextAppearance](/src/main/res/values/styles.xml#L12) style as a `parent`.
2. Specify default clickable text appearance style in your application theme via `clickableTextAppearance` attribute.

Example:
```xml
<style name="YourApplicationTheme" parent="…">
   <!-- other theme attributes omitted -->
   <item name="clickableTextAppearance">@style/TextAppearance.ClickableTextAppearance</item>
</style>

<style name="TextAppearance.ClickableTextAppearance" parent="Base.ClickableTextAppearance">
    <item name="android:textColor">#21834B</item> <!-- let clickable text be green by default -->
</style>
```
> **Note**
> Specified `clickableTextAppearance` can be obtained in code via [ClicableTextAppearance.Companion.from(…)](/src/main/java/com/mmolosay/stringannotations/views/span/clickable/ClickableSpanExt.kt#L17) extension function.