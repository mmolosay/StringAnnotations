package com.mmolosay.stringannotations.views.internal

import android.text.style.CharacterStyle
import com.mmolosay.stringannotations.args.Arguments
import com.mmolosay.stringannotations.processor.AnnotationProcessor
import com.mmolosay.stringannotations.views.args.Clickable

/*
 * Typealiases for internal use.
 */

/**
 * [Arguments] for Android Views system.
 */
internal typealias ViewsArguments = Arguments<Clickable>

/**
 * Type of spans for Android Views system.
 */
internal typealias ViewsSpan = CharacterStyle

/**
 * [AnnotationProcessor] for Android Views system.
 */
internal typealias ViewsAnnotationProcessor = AnnotationProcessor<ViewsArguments, ViewsSpan>