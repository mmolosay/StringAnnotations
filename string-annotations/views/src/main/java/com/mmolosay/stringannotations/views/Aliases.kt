package com.mmolosay.stringannotations.views

import android.text.style.CharacterStyle
import com.mmolosay.stringannotations.args.Arguments
import com.mmolosay.stringannotations.processor.AnnotationProcessor
import com.mmolosay.stringannotations.views.args.Clickable

/*
 * Typealiases for convenience of use.
 */

/**
 * [Arguments] for Android Views system.
 */
public typealias ViewsArguments = Arguments<Clickable>

/**
 * Type of spans for Android Views system.
 */
public typealias ViewsSpan = CharacterStyle

/**
 * [AnnotationProcessor] for Android Views system.
 */
public typealias ViewsAnnotationProcessor = AnnotationProcessor<ViewsArguments, ViewsSpan>