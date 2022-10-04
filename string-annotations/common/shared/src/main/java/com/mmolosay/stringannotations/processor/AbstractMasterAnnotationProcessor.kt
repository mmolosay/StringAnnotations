package com.mmolosay.stringannotations.processor

import android.text.Annotation
import com.mmolosay.stringannotations.args.Arguments

/*
 * Copyright 2022 Mikhail Malasai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * Abstract implementation of [AnnotationProcessor], that delegates processing
 * passed annotation to specific processor.
 *
 * Resolves actual [AnnotationProcessor] to be used with passed annotation, based on
 * its type (attribute).
 * It is able to process all out-of-the-box annotation types.
 *
 * One should inherit this class in order to extend out-of-the-box annotaiton types with
 * custom ones.
 *
 * ## List of default supported annotations:
 *
 * ### Background color
 *
 * Annotation, that specifies background color of its body.
 *
 * __Annotation attribute: `background`__
 *
 * __Arguments qualifier: `background`__
 *
 * __Inline values: —__
 *
 * ```
 * <annotation background="$arg$background$0">text with background</annotation>
 * ```
 *
 * ### Foreground color
 *
 * Annotation, that specifies color of its body.
 *
 * __Annotation attribute: `color`__
 *
 * __Arguments qualifier: `color`__
 *
 * __Inline values: —__
 *
 * ```
 * <annotation color="$arg$color$0">colored text</annotation>
 * ```
 *
 * ### Clickable
 *
 * Annotation, that specifies ability of its body to intercept click events.
 *
 * You should also explicitly specify, that your `TextView` contains clickable text
 * by calling [android.widget.TextView.setMovementMethod].
 *
 * __Annotation attribute: `clickable`__
 *
 * __Arguments qualifier: `clickable`__
 *
 * __Inline values: —__
 *
 * ```
 * <annotation clickable="$arg$clickable$0">clickable text</annotation>
 * ```
 *
 * ### Typeface style
 *
 * __Annotation attribute: `style`__
 *
 * __Arguments qualifier: `style`__
 *
 * __Inline values: —__
 *
 * Annotation, that specifies typeface style of its body.
 *
 * Value of attribute may be combination of "normal", 'bold" and "italic" styles.
 *
 * ```
 * <annotation style="$arg$style$0">text with typeface style</annotaiton>
 * ```
 *
 * ### Strikethrough style
 *
 * Annotation, that crosses its body out.
 *
 * __Annotation attribute: `style`__
 *
 * __Arguments qualifier: —__
 *
 * __Inline values: `strikethrough`__
 *
 * ```
 * <annotation style="strikethrough">crossed out text</annotation>
 * ```
 *
 * ### Underline style
 *
 * Annotation, that underlines its body.
 *
 * __Annotation attribute: `style`__
 *
 * __Arguments qualifier: —__
 *
 * __Inline values: `underline`__
 *
 * ```
 * <annotation style="underline">underlined text</annotation>
 * ```
 *
 * ### Absolute size
 *
 * Annotation, that specifies absolute size of its body (in pixels).
 *
 * __Annotation attribute: `size-absolute`__
 *
 * __Arguments qualifier: `size-absolute`__
 *
 * __Inline values: —__
 *
 * ```
 * <annotation size-absolute="$arg$size-absolute$0">text of absolute size</annotation>
 */
public abstract class AbstractMasterAnnotationProcessor<S> : AnnotationProcessor<S> {

    private val processors: MutableMap<String, AnnotationProcessor<S>> =
        mutableMapOf()

    public final override fun parseAnnotation(
        annotation: Annotation,
        arguments: Arguments?
    ): S? {
        val type = annotation.key
        val processor = getOrCreateAnnotationProcessor(type) ?: return null
        return processor.parseAnnotation(annotation, arguments)
    }

    private fun getOrCreateAnnotationProcessor(type: String): AnnotationProcessor<S>? =
        processors[type] ?: createAnnotationProcessor(type)?.also { processors[type] = it }

    /**
     * Create instance of [AnnotationProcessor], according to [type] of annotation.
     *
     * @param type attribute of string annotation tag.
     *
     * @return appropriate [AnnotationProcessor] instance of `null`, if [type] is not supported.
     */
    protected open fun createAnnotationProcessor(type: String): AnnotationProcessor<S>? =
        when (type) {
            "background" -> createBackgroundColorAnnotationProcessor()
            "color" -> createForegroundColorAnnotationProcessor()
            "style" -> createStyleAnnotationProcessor()
            "clickable" -> createClickableAnnotationProcessor()
            "size-absolute" -> createAbsoluteSizeAnnotationProcessor()
            else -> null
        }

    /**
     * Create instance of [AnnotationProcessor] for 'background' annotation type.
     */
    protected abstract fun createBackgroundColorAnnotationProcessor(): AnnotationProcessor<S>

    /**
     * Create instance of [AnnotationProcessor] for 'color' annotation type.
     */
    protected abstract fun createForegroundColorAnnotationProcessor(): AnnotationProcessor<S>

    /**
     * Create instance of [AnnotationProcessor] for 'style' annotation type.
     */
    protected abstract fun createStyleAnnotationProcessor(): AnnotationProcessor<S>

    /**
     * Create instance of [AnnotationProcessor] for 'clickable' annotation type.
     */
    protected abstract fun createClickableAnnotationProcessor(): AnnotationProcessor<S>

    /**
     * Create instance of [AnnotationProcessor] for 'size-absolute' annotation type.
     */
    protected abstract fun createAbsoluteSizeAnnotationProcessor(): AnnotationProcessor<S>
}