package io.github.mmolosays.stringannotations.processor

import android.text.Annotation
import io.github.mmolosays.stringannotations.processor.parser.ValueParser

/*
 * Copyright 2023 Mikhail Malasai
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
 * ## List of default supported annotations:
 *
 * ### Background color
 *
 * Annotation, that specifies background color of its body.
 *
 * Annotation attribute: `background`
 *
 * Arguments qualifier: `color`
 *
 * ```
 * <annotation background="$arg$color$0">text with background</annotation>
 * ```
 *
 * ### Foreground color
 *
 * Annotation, that specifies color of its body.
 *
 * Annotation attribute: `color`
 *
 * Arguments qualifier: `color`
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
 * Annotation attribute: `clickable`
 *
 * Arguments qualifier: `clickable`
 *
 * ```
 * <annotation clickable="$arg$clickable$0">clickable text</annotation>
 * ```
 *
 * ### Decoration
 *
 * Annotation, that decorates its body.
 *
 * Annotation attribute: `decoration`
 *
 * Arguments qualifier: `decoration`
 *
 * ```
 * <annotation decoration="$arg$decoration$0">decorated text</annotation>
 * ```
 *
 * ### Absolute size
 *
 * Annotation, that specifies absolute size of its body (in pixels).
 *
 * Annotation attribute: `size`
 *
 * Arguments qualifier: `size`
 *
 * ```
 * <annotation size="$arg$size$0">text of absolute size</annotation>
 * ```
 *
 * ### Typeface style
 *
 * Annotation, that specifies typeface style of its body.
 *
 * Annotation attribute: `style`
 *
 * Arguments qualifier: `style`
 *
 * ```
 * <annotation style="$arg$style$0">text with typeface style</annotaiton>
 * ```
 */
public abstract class AbstractMasterAnnotationProcessor<A, S> : AnnotationProcessor<A, S> {

    /**
     * [ValueParser] that can be used by default when creating instances of [AnnotationProcessor].
     */
    protected abstract val defaultValueParser: ValueParser?

    private val processors: MutableMap<String, AnnotationProcessor<A, S>> =
        mutableMapOf()

    public final override fun parseAnnotation(
        annotation: Annotation,
        arguments: A?,
    ): S? {
        val type = annotation.key
        val processor = getOrCreateAnnotationProcessor(type) ?: return null
        return processor.parseAnnotation(annotation, arguments)
    }

    /**
     * Obtains earlier created [AnnotationProcessor] for specified annotation [type].
     * If there's no such, creates a new one and memoizes it.
     */
    private fun getOrCreateAnnotationProcessor(type: String): AnnotationProcessor<A, S>? =
        processors[type] ?: createAnnotationProcessor(type)?.also { processors[type] = it }

    /**
     * Create instance of [AnnotationProcessor], according to [type] of annotation.
     *
     * @param type attribute of string annotation tag.
     *
     * @return appropriate [AnnotationProcessor] instance of `null`, if [type] is not supported.
     */
    protected open fun createAnnotationProcessor(type: String): AnnotationProcessor<A, S>? =
        when (type) {
            AnnotationTypes.background -> createBackgroundColorAnnotationProcessor()
            AnnotationTypes.clickable -> createClickableAnnotationProcessor()
            AnnotationTypes.color -> createForegroundColorAnnotationProcessor()
            AnnotationTypes.decoration -> createDecorationAnnotationProcessor()
            AnnotationTypes.size -> createSizeAnnotationProcessor()
            AnnotationTypes.style -> createStyleAnnotationProcessor()
            else -> null
        }

    /**
     * Create instance of [AnnotationProcessor] for [AnnotationTypes.background] annotation type.
     */
    protected abstract fun createBackgroundColorAnnotationProcessor(): AnnotationProcessor<A, S>

    /**
     * Create instance of [AnnotationProcessor] for [AnnotationTypes.clickable] annotation type.
     */
    protected abstract fun createClickableAnnotationProcessor(): AnnotationProcessor<A, S>

    /**
     * Create instance of [AnnotationProcessor] for [AnnotationTypes.color] annotation type.
     */
    protected abstract fun createForegroundColorAnnotationProcessor(): AnnotationProcessor<A, S>

    /**
     * Create instance of [AnnotationProcessor] for [AnnotationTypes.decoration] annotation type.
     */
    protected abstract fun createDecorationAnnotationProcessor(): AnnotationProcessor<A, S>

    /**
     * Create instance of [AnnotationProcessor] for [AnnotationTypes.size] annotation type.
     */
    protected abstract fun createSizeAnnotationProcessor(): AnnotationProcessor<A, S>

    /**
     * Create instance of [AnnotationProcessor] for [AnnotationTypes.style] annotation type.
     */
    protected abstract fun createStyleAnnotationProcessor(): AnnotationProcessor<A, S>

    /**
     * Types of out-of-the-box annotations.
     */
    public object AnnotationTypes {
        public const val background: String = "background"
        public const val color: String = "color"
        public const val style: String = "style"
        public const val decoration: String = "decoration"
        public const val clickable: String = "clickable"
        public const val size: String = "size"
    }
}