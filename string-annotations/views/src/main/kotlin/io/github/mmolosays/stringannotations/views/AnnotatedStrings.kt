package io.github.mmolosays.stringannotations.views

import android.text.Spannable
import android.text.Spanned
import io.github.mmolosays.stringannotations.AbstractAnnotatedStrings
import io.github.mmolosays.stringannotations.views.internal.SpanProcessor

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
 * Implementation of [AbstractAnnotatedStrings] for Android Views UI.
 */
public object AnnotatedStrings :
    AbstractAnnotatedStrings<ViewsArguments, ViewsSpan, ViewsAnnotationProcessor, Spanned>() {

    override fun getAnnotationProcessor(): ViewsAnnotationProcessor =
        StringAnnotations.dependencies.processor

    override fun applySpans(
        spannable: Spannable,
        spans: List<ViewsSpan?>,
        ranges: List<IntRange>,
    ): Spanned =
        SpanProcessor.applySpans(spannable, spans, ranges)
}