package com.mmolosay.stringannotations.views.internal

import android.text.style.CharacterStyle
import com.mmolosay.stringannotations.processor.AnnotationProcessor

/*
 * Typealiases for internal use.
 */

/**
 * Type of spans for Android Views system.
 */
internal typealias ViewsSpan = CharacterStyle

/**
 * [AnnotationProcessor] for Android Views system.
 */
internal typealias ViewsAnnotationProcessor = AnnotationProcessor<ViewsSpan>