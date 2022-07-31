package com.example.stringannotations.processor

import android.text.Annotation
import android.text.Spanned
import androidx.core.text.getSpans

/**
 * Processes [Spanned] objects.
 */
object SpannedProcessor {

    /**
     * Retrieves spans of [Annotation] type from [string] in their appearance order (left to right).
     *
     * All `<annotation>` tags with more then one attribute will be split into multiple,
     * so that each has only one attribute.
     */
    fun getAnnotationSpans(string: Spanned): Array<out Annotation> =
        string.getSpans()
}