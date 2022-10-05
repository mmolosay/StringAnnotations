package com.mmolosay.stringannotations.compose.internal

import com.mmolosay.stringannotations.compose.processor.ComposeSpan
import com.mmolosay.stringannotations.processor.AnnotationProcessor

/*
 * Typealiases for internal use.
 */

/**
 * [AnnotationProcessor] for Compose UI.
 */
internal typealias ComposeAnnotationProcessor = AnnotationProcessor<ComposeSpan>