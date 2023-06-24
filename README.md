[![Maven metadata URL](https://img.shields.io/maven-metadata/v?color=blue&metadataUrl=https://s01.oss.sonatype.org/service/local/repo_groups/public/content/io/github/mmolosay/stringannotations/maven-metadata.xml&style=for-the-badge)](https://search.maven.org/artifact/io.github.mmolosay/stringannotations)
[![License Apache 2.0](https://img.shields.io/github/license/mmolosay/StringAnnotations.svg?style=for-the-badge&color=orange)](https://opensource.org/licenses/Apache-2.0)
[![kotlin](https://img.shields.io/github/languages/top/mmolosay/StringAnnotations.svg?style=for-the-badge&color=blueviolet)](https://kotlinlang.org/)

# String Annotations
Rich text in your *strings.xml*.

Table of contents
=======

* [Problem to solve](#problem-to-solve)
* [Reasons to use](#reasons-to-use)
* [Artifacts](#artifacts)
    * [Android Views](#android-views)
    * [Jetpack Compose](#jetpack-compose)
* [Installation](#installation)
* [Arguments](#arguments)
* [Annotations](#annotations)
    * [Background color](#background-color)
    * [Foreground color](#foreground-color)
    * [Clickable](#clickable)
    * [Decoration](#decoration)
    * [Absolute size](#absolute-size)
    * [Typeface style](#typeface-style)
* [Samples](#samples)
* [License](#license)

Problem to solve
=======
![image](https://user-images.githubusercontent.com/32337243/183136511-caf3edcf-f34a-4608-9c06-d054c538d6cd.png)

Take a look at a picture above. Think about how you would implement this design in terms of Android.

Yes, with different colors in the same text.
Oh, highlighted parts should perform some action on click as well.

Aren't frightened yet? Well, make it use wildcards and placeholders (e.g. "*group by: &#95;, sort by: &#95;*").
And don't forget about localization.

*B r e a t h e   o u t.*

If you're not new in modern Android development, then you've certainly done things alike before. Indeed, it's pretty common business' request — to reinvigorate monotonous Android `TextView` with some styles in order to provide a better UX.

For my personal surprise, I've never found industry-standard solution, like ones we have for almost everything else in Android. From my experience, every time, when developer needs to do something like this, they either assemble their own solution from related answers, or use copy-pasted piece of code, which they have been using for last few years with minor adjustments each time.

Well, no more :)

**StringAnnotations** is a library that absorbed all best from old good familiar solutions, and enhanced it with great customization, extendability and convenience of use.

Reasons to use
=======

- [X] Annotates string resources in your *strings.xml*, preserving ability for localization.
- [X] Works with `Android Views` system and `Jetpack Compose` UI — choose what you need.
- [X] Provides neat API to use inside `Activity`, `Fragment` and `@Composable` scopes.
- [X] Extendable and customizable: add your own annotation types and arguments for them.
- [X] 100% documented.
- [X] Covered in unit tests. 

Artifacts
=======

### Android Views

`io.github.mmolosay:stringannotations:views:VERSION`

Artifact to be used with Android Views system.

See [string-annotations:views README.md](/string-annotations/views/README.md) for more information.

### Jetpack Compose

`io.github.mmolosay:stringannotations:compose:VERSION`

Artifact to be used with [Jetpack Compose](https://developer.android.com/jetpack/compose) UI.

See [string-annotations:compose README.md](/string-annotations/compose/README.md) for more information.

Installation
=======

The library has not been published yet.

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
val args = Arguments {
    colors(color1, color2)
}
yourTextView.text = getAnnotatedString(R.string.demo, args)
```

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

`<annotation background="$arg$color$0">text with background color</annotation>`

Foreground color
-----
> Specifies foreground color of its body.

![image](https://user-images.githubusercontent.com/32337243/183247974-99b8f693-87bf-4758-9f7d-025a84bb762c.png)

- Annotation attribute: `color`
- Arguments qualifier: `color`

`<annotation color="$arg$color$0">colored text</annotation>`

Clickable
-----
> Specifies ability of its body to intercept click events.

![image](https://user-images.githubusercontent.com/32337243/183248366-d05200a7-c291-480e-a710-f3442d056a39.png)

- Annotation attribute: `clickable`
- Arguments qualifier: `clickable`

`<annotation clickable="$arg$clickable$0">clcik me</annotation>`

Decoration
-----
> Specifies trivial graphic, e.g. line.

![image](https://user-images.githubusercontent.com/32337243/183249199-4ee9923a-9cfb-4731-a6a6-a91693c6410d.png)
![image](https://user-images.githubusercontent.com/32337243/183249071-efe2a8f7-e2a6-4396-a78b-63ea20901b4c.png)

- Annotation attribute: `decoration`
- Arguments qualifier: `decoration`

* `<annotation decoration="$arg$decoration$0">decorated text</annotation>`
 
Absolute size
-----
> Specifies absolute size of its body.

![image](https://user-images.githubusercontent.com/32337243/186401072-af503ada-80dd-4dc1-a5b9-c0b05ebbf89c.png)

- Annotation attribute: `size`
- Arguments qualifier: `size`

`<annotation size="$arg$size$0">text of some size</annotation>`

Typeface style
-----
> Specifies typeface style of its body.

![image](https://user-images.githubusercontent.com/32337243/183248716-5ca79fb4-a27f-4155-b75a-07ee28cbd203.png)

- Annotation attribute: `style`
- Arguments qualifier: `style`

`<annotation style="$arg$style$0">styled text</annotation>`

Samples
======

Check out [Android Views](/sample-views) and [Jetpack Compose](/sample-compose) samples.

License
=======
```text
Copyright 2023 Mikhail Malasai

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
