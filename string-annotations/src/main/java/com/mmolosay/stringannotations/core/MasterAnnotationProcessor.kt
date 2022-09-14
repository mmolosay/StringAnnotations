package com.mmolosay.stringannotations.core

import android.content.Context
import android.text.Annotation
import android.text.style.CharacterStyle
import com.mmolosay.stringannotations.args.ArgumentsSet
import com.mmolosay.stringannotations.processor.AbsoluteSizeAnnotationProcessor
import com.mmolosay.stringannotations.processor.BackgroundColorAnnotationProcessor
import com.mmolosay.stringannotations.processor.ClickableAnnotationProcessor
import com.mmolosay.stringannotations.processor.ForegroundColorAnnotationProcessor
import com.mmolosay.stringannotations.processor.StyleAnnotationProcessor

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
 * Default implementation of [AnnotationProcessor], that works with [ArgumentsSet].
 * Resolves actual [AnnotationProcessor] to be used with passed annotation, based on
 * its type (attribute).
 * It is able to process all default annotation types.
 *
 * One should inherit this class in order to add support for custom annotation type.
 *
 * ## List of default supported annotations:
 *
 * ### Background color
 *
 * Annotation, that specifies background color of its body.
 *
 * ```
 * HEX color:
 * <annotation background="#ff0000">text with red background</annotation>
 *
 * Generic color name:
 * <annotation background="green">text with green background</annotation>
 * ```
 *
 * ### Foreground color
 *
 * Annotation, that specifies color of its body.
 *
 * ```
 * HEX color:
 * <annotation color="#ff0000">red text</annotation>
 *
 * Generic color name:
 * <annotation color="green">green text</annotation>
 * ```
 *
 * ### Clickable
 *
 * Annotation, that specifies ability of its body to intercept click events.
 *
 * Use runtime value arguments to specify span for the annotation.
 *
 * You should also explicitly specify, that your `TextView` contains clickable text
 * by calling [android.widget.TextView.setMovementMethod].
 *
 * ```
 * <annotation clickable="arg$clickable$0">clickable text</annotation>
 * ```
 *
 * ### Typeface style
 *
 * Annotation, that specifies typeface style of its body.
 *
 * Value of attribute may be combination of "normal", 'bold" and "italic" styles.
 *
 * ```
 * <annotation style="bold|italic">bold and italic text</annotaiton>
 * ```
 *
 * ### Strikethrough style
 *
 * Annotation, that crosses its body out.
 *
 * ```
 * <annotation style="strikethrough">crossed out text</annotation>
 * ```
 *
 * ### Underline style
 *
 * Annotation, that underlines its body.
 *
 * ```
 * <annotation style="underline">underlined text</annotation>
 * ```
 *
 * ### Absolute size
 *
 * Annotation, that specifies absolute size of its body.
 *
 * ```
 * Pixels (just size will be treated the same):
 * <annotation size-absolute="20.3px">text of 20.3 px size</annotation>
 *
 * DPs:
 * <annotation size-absolute="20.3dp">text of 20.3 DP size</annotation>
 *
 * SPs:
 * <annotation size-absolute="20.3sp">text of 20.3 SP size</annotation>
 * ```
 */
public open class MasterAnnotationProcessor : AnnotationProcessor<ArgumentsSet> {

    private val processors: MutableMap<String, AnnotationProcessor<ArgumentsSet>> =
        mutableMapOf()

    final override fun parseAnnotation(
        context: Context,
        annotation: Annotation,
        arguments: ArgumentsSet?
    ): CharacterStyle? {
        val type = annotation.key
        val processor = getOrCreateAnnotationProcessor(type) ?: return null
        return processor.parseAnnotation(context, annotation, arguments)
    }

    private fun getOrCreateAnnotationProcessor(type: String): AnnotationProcessor<ArgumentsSet>? =
        processors[type] ?: createAnnotationProcessor(type)?.also { processors[type] = it }

    /**
     * Create instance of [AnnotationProcessor], according to [type] of annotation.
     *
     * @param type attribute of string annotation tag.
     *
     * @return appropriate [AnnotationProcessor] instance of `null`, if [type] is not supported.
     */
    protected open fun createAnnotationProcessor(type: String): AnnotationProcessor<ArgumentsSet>? =
        when (type) {
            "background" -> BackgroundColorAnnotationProcessor()
            "color" -> ForegroundColorAnnotationProcessor()
            "style" -> StyleAnnotationProcessor()
            "clickable" -> ClickableAnnotationProcessor()
            "size-absolute" -> AbsoluteSizeAnnotationProcessor()
            else -> null
        }
}