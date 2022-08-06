[![](https://jitpack.io/v/mmolosay/stringannotations.svg)](https://jitpack.io/#mmolosay/stringannotations)

# String Annotations
Ultimate annotations in your *strings.xml*.

## Table of contents

* [Problem to solve](#problem-to-solve)
* [Reasons to use](#reasons-to-use)
* [Installation](#installation)
* [Annotation types](#annotation-types)
* [Example of usage](#example-of-usage)
* [License](#license)

------

## Problem to solve
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

## Reasons to use

1. Concept is old and well-known.
2. Library is pleasant to use: it's extendable, customizable and has a lot out-of-the-box features.
3. Has extensions for regular Android components, like `Activity` and `Fragment`.
4. Small source code size.
5. 100% documented.

## Installation

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

## Annotation types

There are all supported by default types of annotations.
You can extend it and implement your own ones.

* #### Background color
	Uses attribute `background`.
	Specifies background color of its body.

	Examples:
	 * HEX color:
	 ` <annotation background="#ff0000">text with red background</annotation>`
	 *  Generic color name:
	 `<annotation background="green">text with green background</annotation>`
	 * Color resource name:
	 `<annotation background="yourColorResName">text with colored background</annotation>`

## Example of usage

Following **simplified** examples demonstrate ways to use `StringAnnotations`:

Full sample can be found in [sample](/sample) module.

```kotlin

```

## License
