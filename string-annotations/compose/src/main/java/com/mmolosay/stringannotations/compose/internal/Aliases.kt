package com.mmolosay.stringannotations.compose.internal

import com.mmolosay.stringannotations.args.Arguments
import com.mmolosay.stringannotations.compose.args.Clickable
import com.mmolosay.stringannotations.compose.processor.ComposeSpan
import com.mmolosay.stringannotations.processor.AnnotationProcessor

/*
 * Typealiases for internal use.
 */

/**
 * [Arguments] for Compose UI.
 */
internal typealias ComposeArguments = Arguments<Clickable>

/**
 * [AnnotationProcessor] for Compose UI.
 */
internal typealias ComposeAnnotationProcessor = AnnotationProcessor<ComposeArguments, ComposeSpan>