package io.github.mmolosays.stringannotations.compose

import android.text.Spannable
import androidx.compose.ui.text.AnnotatedString
import io.github.mmolosays.stringannotations.AbstractAnnotatedStrings
import io.github.mmolosays.stringannotations.compose.internal.SpanProcessor
import io.github.mmolosays.stringannotations.compose.processor.ComposeSpan

/*
 * Copyright 2023 Mikhail Malasai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * Implementation of [AbstractAnnotatedStrings] for Compose UI.
 */
public object AnnotatedStrings :
    AbstractAnnotatedStrings<ComposeArguments, ComposeSpan, ComposeAnnotationProcessor, AnnotatedString>() {

    override fun getAnnotationProcessor(): ComposeAnnotationProcessor =
        StringAnnotations.dependencies.processor

    override fun applySpans(
        spannable: Spannable,
        spans: List<ComposeSpan?>,
        ranges: List<IntRange>,
    ): AnnotatedString =
        SpanProcessor.applySpans(spannable, spans, ranges)
}