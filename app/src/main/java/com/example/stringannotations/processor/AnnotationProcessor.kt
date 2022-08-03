package com.example.stringannotations.processor

import android.content.Context
import android.text.Annotation
import android.text.style.CharacterStyle
import android.text.style.ClickableSpan

/**
 * Parses [Annotation] of some [android.text.Spanned] string into span of [CharacterStyle] type.
 */
sealed interface AnnotationProcessor {

    /**
     * Parses specified [annotation] into span of [CharacterStyle] type.
     *
     * @param context caller context.
     * @param annotation annotation to be parsed.
     * @param clickables list of [ClickableSpan], that will be used for clickable spans.
     *
     * @return parsed span of [CharacterStyle] type, or `null`, if annotation is unsupported or
     * unparseable.
     */
    fun parseAnnotation(
        context: Context,
        annotation: Annotation,
        clickables: List<ClickableSpan>
    ): CharacterStyle?
}