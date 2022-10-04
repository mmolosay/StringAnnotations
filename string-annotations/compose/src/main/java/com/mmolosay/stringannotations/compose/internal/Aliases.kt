package com.mmolosay.stringannotations.compose.internal

import androidx.compose.ui.text.SpanStyle
import com.mmolosay.stringannotations.processor.AnnotationProcessor

/*
 * Typealiases for internal use.
 */

/**
 * [AnnotationProcessor] for Compose UI.
 */
// TODO: since AnnotatedString works with both SpanStyle and ParagraphStyle, make custom
//       wrapper object for them and use it as span type of AnnotationProcessor
internal typealias ComposeAnnotationProcessor = AnnotationProcessor<SpanStyle>