package com.mmolosay.stringannotations.mapper

import android.content.Context
import android.text.Annotation
import android.text.Spanned
import android.text.style.CharacterStyle
import android.text.style.ClickableSpan
import com.mmolosay.stringannotations.StringAnnotation
import com.mmolosay.stringannotations.processor.AnnotationProcessor

/**
 * Maps [Annotation] instances into other ones.
 */
internal object AnnotationMapper {

    /**
     * Parses specified [annotations] of [string] into list of [StringAnnotation].
     */
    fun parseStringAnnotations(
        string: Spanned,
        annotations: Array<out Annotation>
    ): List<StringAnnotation> =
        annotations.mapIndexed { index, annotation ->
            val range = parseAnnotationRange(string, annotation)
            if (range.first == -1 || range.last == -1) {
                throw IllegalArgumentException("annotation doesn\'t belong to this string")
            }
            StringAnnotation(annotation, range.first, range.last, index)
        }

    /**
     * Retrieves [annotation]'s start and end positions in terms of specified [string].
     * If [annotation] is `null`, then we assume that is a top-most root, and return full [string]
     * range.
     */
    fun parseAnnotationRange(
        string: Spanned,
        annotation: Annotation?
    ): IntRange {
        annotation ?: return 0..string.length
        val start = string.getSpanStart(annotation)
        val end = string.getSpanEnd(annotation)
        return start..end
    }

    /**
     * Uses specified [processor] to process [annotations] of
     * some spanned string into spans of [CharacterStyle] type.
     */
    fun parseAnnotations(
        context: Context,
        processor: AnnotationProcessor,
        annotations: Array<out Annotation>,
        clickables: List<ClickableSpan>
    ): List<CharacterStyle?> =
        annotations.map { annotation ->
            processor.parseAnnotation(context, annotation, clickables)
        }
}