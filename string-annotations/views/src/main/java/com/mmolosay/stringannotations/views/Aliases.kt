package com.mmolosay.stringannotations.views

import android.text.style.CharacterStyle
import com.mmolosay.stringannotations.args.values.AnnotationValues
import com.mmolosay.stringannotations.processor.AnnotationProcessor
import com.mmolosay.stringannotations.views.args.Clickable

/*
 * Typealiases for convenience of use.
 */

/**
 * [AnnotationValues] for Android Views UI.
 */
public typealias ViewsAnnotationValues = AnnotationValues<Clickable>

/**
 * Type of spans for Android Views UI.
 */
public typealias ViewsSpan = CharacterStyle

/**
 * [AnnotationProcessor] for Android Views UI.
 */
public typealias ViewsAnnotationProcessor = AnnotationProcessor<ViewsSpan>