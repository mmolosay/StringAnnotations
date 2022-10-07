package com.mmolosay.stringannotations.compose

import com.mmolosay.stringannotations.args.AnnotationValues
import com.mmolosay.stringannotations.compose.args.Clickable
import com.mmolosay.stringannotations.compose.processor.ComposeSpan
import com.mmolosay.stringannotations.processor.AnnotationProcessor

/*
 * Typealiases for convenience of use.
 */

/**
 * [AnnotationValues] for Compose UI.
 */
public typealias ComposeAnnotationValues = AnnotationValues<Clickable>

/**
 * [AnnotationProcessor] for Compose UI.
 */
public typealias ComposeAnnotationProcessor = AnnotationProcessor<ComposeSpan>