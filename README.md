[![](https://jitpack.io/v/mmolosay/stringannotations.svg)](https://jitpack.io/#mmolosay/stringannotations)
[![License](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](https://www.apache.org/licenses/LICENSE-2.0)

# String Annotations
Ultimate annotations in your *strings.xml*.

Table of contents
=======

* [Problem to solve](#problem-to-solve)
* [Reasons to use](#reasons-to-use)
* [Installation](#installation)
* [Configuration](#configuration)
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
* [Value Arguments](#value-arguments)
* [Examples of usage](#examples-of-usage)
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

1. Concept is old and well-known.
2. Library is pleasant to use: it's extendable, customizable and has a lot out-of-the-box features.
3. Has extensions for regular Android components, like `Activity` and `Fragment`.
4. Small source code size.
5. 100% documented.

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
    implementation("com.github.mmolosay:stringannotations:VERSION")
}
```

Where `VERSION` is the version of desired release. It can be obtained on [releases](https://github.com/mmolosay/StringAnnotations/releases) page. 
Latest release version is stated at the top of this document in JitPack badge.

Configuration
=======

Once you've installed the library, you should configure it. Use [StringAnnotations.configure()](/string-annotations/src/main/java/com/mmolosay/stringannotations/core/StringAnnotations.kt#L36) method, which provides [StringAnnotations.Builder](/string-annotations/src/main/java/com/mmolosay/stringannotations/core/StringAnnotations.kt#L60) instance and configure the library in desired way.

There's an example of configuring the library in your `Application` class:

```kotlin
class YourApplication : Application() {

   override fun onCreate() {
      // irrelevant code is omitted
      configureStringAnnotations()
   }
   
   private fun configureStringAnnotations() {
      val processor = DefaultAnnotationProcessor()
      StringAnnotations.configure()
         .annotationProcessor(processor)
   }
}
```

[AnnotationProcessor](/string-annotations/src/main/java/com/mmolosay/stringannotations/core/StringAnnotations.kt#L64)
-----
You should define [AnnotationProcessor](/string-annotations/src/main/java/com/mmolosay/stringannotations/processor/AnnotationProcessor.kt) instance to be used.

If you're not planning on using custom annotations, then you should pass [DefaultAnnotationProcessor](/string-annotations/src/main/java/com/mmolosay/stringannotations/processor/DefaultAnnotationProcessor.kt) instance.
Otherwise, pass your own implementation.

*Note: [AnnotationProcessor](/string-annotations/src/main/java/com/mmolosay/stringannotations/processor/AnnotationProcessor.kt) is declared as `sealed` in order to make [DefaultAnnotationProcessor](/string-annotations/src/main/java/com/mmolosay/stringannotations/processor/DefaultAnnotationProcessor.kt) a single base class for custom implementations, since it insapsulates a lot of annotation parsing functionality. You should use it as a base class for your implementation of `AnnotationProcessor`.*

[ClickableTextAppearance](/string-annotations/src/main/java/com/mmolosay/stringannotations/ClickableTextAppearance.kt)
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

*Note: Specified `ClickableTextAppearance` instance can be obtained via [ClicableTextAppearance.from(…)](/string-annotations/src/main/java/com/mmolosay/stringannotations/ClickableTextAppearanceExt.kt#L33) extension function.*

Annotations
=======

There are types of annotations, supported by default.
You can extend it and implement your own ones.

Background color
-----
> Specifies background color of its body.

![image](https://user-images.githubusercontent.com/32337243/183247809-3d087191-aa14-4d93-bcff-f69018b68ec7.png)

Uses attribute `background`.

Attribute value can be either HEX color, generic color name or value argument of type `color`.

Examples:
 * HEX color:
 ` <annotation background="#ff0000">text with red background</annotation>`
 *  Generic color name:
 `<annotation background="green">text with green background</annotation>`
 * Value argument:
 `<annotation color="$arg$color$0">colored text</annotation>`

Foreground color
-----
> Specifies foreground color of its body.

![image](https://user-images.githubusercontent.com/32337243/183247974-99b8f693-87bf-4758-9f7d-025a84bb762c.png)

Uses attribute `color`.

Attribute value can be either HEX color, generic color name or value argument of type `color`.

Examples:
 * HEX color:
 ` <annotation color="#ff0000">red text</annotation>`
 *  Generic color name:
 `<annotation color="green">green text</annotation>`
 * Value argument:
 `<annotation color="$arg$color$0">colored text</annotation>`

Clickable
-----
> Specifies ability of its body to intercept click events.

![image](https://user-images.githubusercontent.com/32337243/183248366-d05200a7-c291-480e-a710-f3442d056a39.png)

Uses attribute `clickable`.

Attribute value is a value argument of type `clickable`.

Example:
 *  `<annotation clickable="$arg$clickable$0">clcik me</annotation> and <annotation clickable="$arg$clickable$1">me as well</annotation>`

Typeface style
-----
> Specifies typeface style of its body.

![image](https://user-images.githubusercontent.com/32337243/183248716-5ca79fb4-a27f-4155-b75a-07ee28cbd203.png)

Uses attribute `style`.

Attribute value can be either `normal`, `bold`, `italic`, `bold|italic` or value argument of type `style`.

Examples:
 * Bold text:
 `<annotation style="bold">bold text</annotation>`
 * Italic text:
 `<annotation style="italic">italic text</annotation>`
 * Bold and italic text:
 `<annotation style="bold|italic">bold and italic text</annotation>`
 * Value argument:
 `<annotation style="$arg$style$0">styled text</annotation>`

Strikethrough style
-----
> Crosses out its body.

![image](https://user-images.githubusercontent.com/32337243/183249071-efe2a8f7-e2a6-4396-a78b-63ea20901b4c.png)

Uses attribute `style` and value `strikethrough`.

Example:
 * `<annotation style="strikethrough">crossed out text</annotation>`

Underline style
-----
> Underlines its body.

![image](https://user-images.githubusercontent.com/32337243/183249199-4ee9923a-9cfb-4731-a6a6-a91693c6410d.png)

Uses attribute `style` and value `underline`.

Example:
 * `<annotation style="underline">underlined text</annotation>`
 
Absolute size
-----
> Specifies absolute size of its body.

![image](https://user-images.githubusercontent.com/32337243/186401072-af503ada-80dd-4dc1-a5b9-c0b05ebbf89c.png)

Uses attribute `size-absolute`.

Attribute value is a decimal/integer value, followed by either one of `px`(optional), `dp` or `sp` unit label or value attribute of type `size-absolute` in pixels.

Examples:
 * Pixels, a.k.a `px` (can be omitted):
 `<annotation size-absolute="20.5px">text of 20.5 PXs size</annotation>`
 * Density-independent Pixels, a.k.a. `dp`:
 `<annotation size-absolute="20.5dp">text of 20.5 DPs size</annotation>`
 * Scalable Pixels, a.k.a. `sp`:
 `<annotation size-absolute="20.5sp">text of 20.5 SPs size</annotation>`
 * Value argument:
 `<annotation size-absolute="$arg$size-absolute$0">text of some size</annotation>`

Value Arguments
======
Value Arguments is a concept of runtime values, which are used as values of actual spans, parsed from string annotations.
They make it possible to define `onClick` action for `ClickalbeSpan` or use color obtained from resources in runtime.

Default implementation [ValueArgs](/string-annotations/src/main/java/com/mmolosay/stringannotations/args/ValueArgs.kt) supports values for all
types of [DefaultAnnotationProcessor](/string-annotations/src/main/java/com/mmolosay/stringannotations/processor/DefaultAnnotationProcessor.kt) annotations.

Since [DefaultAnnotationProcessor](/string-annotations/src/main/java/com/mmolosay/stringannotations/processor/DefaultAnnotationProcessor.kt) uses [DefaultAnnotationValueProcessor](/string-annotations/src/main/java/com/mmolosay/stringannotations/processor/DefaultAnnotationValueProcessor.kt) to parse value arguments, default placeholders' format is `$arg${TYPE}${INDEX}`.

Below you can see **simplified** example, how Value Arguments can be used.
For more examples, check [sample application](/sample).

In your *strings.xml*:
```xml
<string name="demo">Text with <annotation color="$arg$color$0">different</annotation> <annotation color="$arg$color$1">runtime</annotation> colors</string>
```

In your code:
```kotlin
val color1 = ContextCompat.getColor(this, R.color.red)
val color2 = ContextCompat.getColor(this, R.color.green)
val args = ValueArgs {
   colors {
      add(color1)
      add(color2)
   }
}
yourTextView.text = getAnnotatedString(R.string.demo, args)
```

Examples of usage
======

Following **simplified** examples demonstrate ways to use `StringAnnotations`:

Full sample can be found in [sample](/sample) module.

```kotlin

```

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
