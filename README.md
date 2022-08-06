[![](https://jitpack.io/v/mmolosay/stringannotations.svg)](https://jitpack.io/#mmolosay/stringannotations)

# String Annotations
Ultimate annotations in your *strings.xml*.

Table of contents
=======

* [Problem to solve](#problem-to-solve)
* [Reasons to use](#reasons-to-use)
* [Installation](#installation)
* [Annotations](#annotations)
    * [Background color](#background-color)
    * [Foreground color](#foreground-color)
    * [Clickable](#clickable)
    * [Typeface style](#typeface-style)
    * [Strikethrough style](#strikethrough-style)
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

*Breathe out.*

If you're not new in modern Android development, then you've certainly done things alike before. Indeed, it's pretty common business' request — to enliven monotonous Android `TextView` with some styles in order to provide a better UX.

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

Annotations
=======

There are types of annotations, supported by default.
You can extend it and implement your own ones.

Background color
-----
> Specifies background color of its body.

![image](https://user-images.githubusercontent.com/32337243/183247809-3d087191-aa14-4d93-bcff-f69018b68ec7.png)

Uses attribute `background`.
Attribute value can be either HEX color, generic color name or name of your color resource.

Examples:
 * HEX color:
 ` <annotation background="#ff0000">text with red background</annotation>`
 *  Generic color name:
 `<annotation background="green">text with green background</annotation>`
 * Color resource name:
 `<annotation background="yourColorResName">text with colored background</annotation>`

Foreground color
-----
> Specifies foreground color of its body.

![image](https://user-images.githubusercontent.com/32337243/183247974-99b8f693-87bf-4758-9f7d-025a84bb762c.png)

Uses attribute `color`.
Attribute value can be either HEX color, generic color name or name of your color resource.

Examples:
 * HEX color:
 ` <annotation color="#ff0000">red text</annotation>`
 *  Generic color name:
 `<annotation color="green">green text</annotation>`
 * Color resource name:
 `<annotation color="yourColorResName">colored text</annotation>`

Clickable
-----
> Specifies ability of its body to intercept click events.

![image](https://user-images.githubusercontent.com/32337243/183248366-d05200a7-c291-480e-a710-f3442d056a39.png)

Uses attribute `clickable`.
Attribute value is an index, at which corresponding `ClickableSpan` is located.

You should provide a list of clickables, if you request annotated string with clickable sections.

Example:
 *  `<annotation clickable="0">clcik me</annotation> and <annotation clickable="1">me as well</annotation>`

Typeface style
-----
> Specifies typeface style of its body.

![image](https://user-images.githubusercontent.com/32337243/183248716-5ca79fb4-a27f-4155-b75a-07ee28cbd203.png)

Uses attribute `style`.
Attribute value can be either `normal`, `bold`, `italic`, or `bold|italic`.

Examples:
 * Bold text:
 `<annotation style="bold">bold text</annotation>`
 * Italic text:
 `<annotation style="italic">italic text</annotation>`
 * Bold and italic text:
 `<annotation style="bold|italic">bold and italic text</annotation>`

Strikethrough styles
-----
> Crosses out its body.

![image](https://user-images.githubusercontent.com/32337243/183249071-efe2a8f7-e2a6-4396-a78b-63ea20901b4c.png)

Uses attribute `style` and value `strikethrough`.

Example:
 * `<annotation style="strikethrough">crossed out text</annotation>`

Examples of usage
======

Following **simplified** examples demonstrate ways to use `StringAnnotations`:

Full sample can be found in [sample](/sample) module.

```kotlin

```

## License
