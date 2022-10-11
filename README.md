[![](https://jitpack.io/v/mmolosay/stringannotations.svg)](https://jitpack.io/#mmolosay/stringannotations)
[![License](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](https://www.apache.org/licenses/LICENSE-2.0)

# String Annotations
Ultimate annotations in your *strings.xml*.

Table of contents
=======

* [Problem to solve](#problem-to-solve)
* [Reasons to use](#reasons-to-use)
* [Artifacts](#artifacts)
    * [Android Views](#android-views)
    * [Jetpack Compose](#jetpack-compose)
* [Installation](#installation)
* [General configuration](#general-configuration)
    * [AnnotationProcessor](#annotationprocessor)
    * [ClickableTextAppearance](#clickabletextappearance)
* [Annotations](#annotations)
    * [Background color](#background-color)
    * [Foreground color](#foreground-color)
    * [Clickable](#clickable)
    * [Typeface style](#typeface-style)
    * [Strikethrough style](#strikethrough-style)
    * [Underline style](#underline-style)
    * [Absolute size](#absolute-size)
* [Annotation Arguments](#annotation-arguments)
* [Samples](#samples)
* [License](#license)

Problem to solve
=======
![image](https://user-images.githubusercontent.com/32337243/183136511-caf3edcf-f34a-4608-9c06-d054c538d6cd.png)

Take a look at a picture above. Think about how you would implement this design in terms of Android.

Yes, with different colors of text in same view.
Oh, highlighted parts should open different screen on click.

Aren't frightened yet? Well, make it use wildcards and placeholders (e.g. "group by: &#95;, sort by: &#95;").
And don't forget about localization.

*B r e a t h e   o u t.*

If you're not new in modern Android development, then you've certainly done things alike before. Indeed, it's pretty common business' request — to reinvigorate monotonous Android `TextView` with some styles in order to provide a better UX.

For my personal surprise, I've never found industry-standard solution, like ones we have for almost everything else in Android. From my experience, every time, when developer needs to do something like this, they either assemble their own solution from related answers, or use copy-pasted piece of code, which they have been using for last few years with minor adjustments each time.

Well, no more :)

**String Annotations** is a library that absorbed all best from old good familiar solutions, and enhanced it with great customization, extendability and convenience of use.

Reasons to use
=======

1. Annotates string resources in your *strings.xml*, preserving ability for localization.
2. Works with `Android Views` system and `Jetpack Compose` UI — choose what you need.
3. Provides neat API to use in the `Activity`, `Fragment` and `@Composable` scopes.
4. Extendable and customizable: add your own annotation types and arguments for them.
5. 100% documented.

Artifacts
=======

### Android Views

`com.github.mmolosay:stringannotations:views:VERSION`

Artifact to be used with Android Views system.

See [string-annotations:views README.md](/string-annotations/views/README.md) for more information.

### Jetpack Compose

`com.github.mmolosay:stringannotations:compose:VERSION`

Artifact to be used with [Jetpack Compose](https://developer.android.com/jetpack/compose) UI.

See [string-annotations:compose README.md](/string-annotations/compose/README.md) for more information.

Installation
=======

Use [JitPack](https://www.jitpack.io) to add it as a dependency to your Kotlin project.
Code snippet below shows way for adding it
via [Gradle Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html):

```kotlin
repositories {
    mavenCentral()
    maven("https://jitpack.io") // make sure to use jitpack repository
}

dependencies {
    implementation("com.github.mmolosay:stringannotations:ARTIFACT:VERSION")
}
```

Where:
 - `ARTIFACT` is an implementation for desired UI system. See [Artifacts](#artifacts) section for more details.
 - `VERSION` is the version of desired release. It can be obtained on [releases](https://github.com/mmolosay/StringAnnotations/releases) page. 
Latest release version is stated at the top of this document in JitPack badge.

General configuration
=======

In order to alter default behaviour, you should call [StringAnnotations.configure(…)](/string-annotations/common/shared/src/main/java/com/mmolosay/stringannotations/core/StringAnnotations.kt#L42) method and provide custom dependencies. 

> **Note**
> There's **no need** to call the method, if you want to use library in its default configuration (which is enough in majority of cases).

For examples of library customization check out [samples](#samples) section.

AnnotationProcessor
-----
If no custom [AnnotationProcessor](/string-annotations/src/main/java/com/mmolosay/stringannotations/core/AnnotationProcessor.kt) is defined, [MasterAnnotationProcessor](/string-annotations/src/main/java/com/mmolosay/stringannotations/core/MasterAnnotationProcessor.kt) will be used by default.

*Note: Although [AnnotationProcessor](/string-annotations/src/main/java/com/mmolosay/stringannotations/core/AnnotationProcessor.kt) may be directly implemented, author recommends to use [MasterAnnotationProcessor](/string-annotations/src/main/java/com/mmolosay/stringannotations/core/MasterAnnotationProcessor.kt) along with [BaseAnnotationProcessor](/string-annotations/src/main/java/com/mmolosay/stringannotations/processor/BaseAnnotationProcessor.kt), since it incapsulates a lot of annotation parsing functionality.*

ClickableTextAppearance
-----
You should define default appearance of your [Clickable](#clickable) annotations text.

1. (optional) Define your own style for clickable text appearance. You can use [Base.ClickableTextAppearance](/string-annotations/src/main/res/values/styles.xml#L12) style as a `parent`.
2. Specify default clickable text appearance style in your application theme via `clickableTextAppearance` attribute.

Example:
```xml
<style name="YourApplicationTheme" parent="…">
   <!-- other theme attributes -->
   <item name="clickableTextAppearance">@style/TextAppearance.ClickableTextAppearance</item>
</style>

<style name="TextAppearance.ClickableTextAppearance" parent="Base.ClickableTextAppearance">
    <item name="android:textColor">#21834B</item> <!-- let clickable text be green by default -->
</style>
```

*Note: Specified `ClickableTextAppearance` instance can be obtained via [ClicableTextAppearance.from(…)](/string-annotations/src/main/java/com/mmolosay/stringannotations/core/ClickableTextAppearance.kt#L49) extension function.*

Annotations
=======

There are types of annotations, supported by default.
You can extend it and implement your own ones.

Background color
-----
> Specifies background color of its body.

![image](https://user-images.githubusercontent.com/32337243/183247809-3d087191-aa14-4d93-bcff-f69018b68ec7.png)

- Annotation attribute: `background`
- Arguments qualifier: `color`
- Inline values: *none*

`<annotation background="$arg$color$0">text with background color</annotation>`

Foreground color
-----
> Specifies foreground color of its body.

![image](https://user-images.githubusercontent.com/32337243/183247974-99b8f693-87bf-4758-9f7d-025a84bb762c.png)

- Annotation attribute: `color`
- Arguments qualifier: `color`
- Inline values: *none*

`<annotation color="$arg$color$0">colored text</annotation>`

Clickable
-----
> Specifies ability of its body to intercept click events.

![image](https://user-images.githubusercontent.com/32337243/183248366-d05200a7-c291-480e-a710-f3442d056a39.png)

- Annotation attribute: `clickable`
- Arguments qualifier: `clickable`
- Inline values: *none*

`<annotation clickable="$arg$clickable$0">clcik me</annotation>`

Decoration
-----
> Specifies trivial graphic, e.g. line.

![image](https://user-images.githubusercontent.com/32337243/183249199-4ee9923a-9cfb-4731-a6a6-a91693c6410d.png)
![image](https://user-images.githubusercontent.com/32337243/183249071-efe2a8f7-e2a6-4396-a78b-63ea20901b4c.png)

- Annotation attribute: `decoration`
- Arguments qualifier: `decoration`
- Inline values: `underline`, `strikethrough`

* `<annotation decoration="underline">underlined text</annotation>`
* `<annotation decoration="strikethrough">crossed out text</annotation>`
* `<annotation decoration="$arg$decoration$0">decorated text</annotation>`
 
Absolute size
-----
> Specifies absolute size of its body.

![image](https://user-images.githubusercontent.com/32337243/186401072-af503ada-80dd-4dc1-a5b9-c0b05ebbf89c.png)

- Annotation attribute: `size`
- Arguments qualifier: `size`
- Inline values: *none*

`<annotation size="$arg$size$0">text of some size</annotation>`

Typeface style
-----
> Specifies typeface style of its body.

![image](https://user-images.githubusercontent.com/32337243/183248716-5ca79fb4-a27f-4155-b75a-07ee28cbd203.png)

- Annotation attribute: `style`
- Arguments qualifier: `style`
- Inline values: *none*

`<annotation style="$arg$style$0">styled text</annotation>`

Arguments
======
`Arguments` provide values for your annotations. 
You should pass `Arguments` instance, containing all declared values, when obtaining annotated string.

Default [Arguments](string-annotations/common/shared/src/main/java/com/mmolosay/stringannotations/args/Arguments.kt) implementation provides values for all out-of-the-box annotation types.

Since default implementations of `AnnotationProcessor` use [DefaultValuesParser](string-annotations/common/shared/src/main/java/com/mmolosay/stringannotations/processor/parser/DefaultValuesParser.kt) to parse arguments, default placeholders' format is `$arg${QUALIFIER}${INDEX}`, where `QUALIFIER` 
should match the `qualifier` of corresponding annotation type and `INDEX` specifies index of desired
value.

Below you can see **simplified** example, how `Arguments` can be used.
For more examples, view [samples](#samples).

In your *strings.xml*:
```xml
<string name="demo">Text with <annotation color="$arg$color$0">multiple</annotation> <annotation color="$arg$color$1">colors</annotation></string>
```

In your code:
```kotlin
val color1 = ContextCompat.getColor(this, R.color.red)
val color2 = ContextCompat.getColor(this, R.color.green)
val args = ArgumentsSet {
    colors(color1, color2)
}
yourTextView.text = getAnnotatedString(R.string.demo, args)
```

Samples
======

Check out [Android Views](/sample-views) and [Jetpack Compose](/sample-compose) samples.

License
=======
```text
Copyright 2022 Mikhail Malasai

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
