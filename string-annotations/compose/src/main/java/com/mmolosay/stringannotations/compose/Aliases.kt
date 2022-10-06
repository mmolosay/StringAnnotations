package com.mmolosay.stringannotations.compose

import com.mmolosay.stringannotations.args.Arguments
import com.mmolosay.stringannotations.compose.args.Clickable
import com.mmolosay.stringannotations.compose.processor.ComposeSpan
import com.mmolosay.stringannotations.processor.AnnotationProcessor

/*
 * Typealiases for convenience of use.
 */

/**
 * [Arguments] for Compose UI.
 */
public typealias ComposeArguments = Arguments<Clickable>

/**
 * [AnnotationProcessor] for Compose UI.
 */
public typealias ComposeAnnotationProcessor = AnnotationProcessor<ComposeArguments, ComposeSpan>