package com.mmolosay.stringannotations.compose.processor

import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.style.TextDecoration
import com.mmolosay.stringannotations.compose.internal.ComposeSpan
import com.mmolosay.stringannotations.internal.processor.BaseStyleAnnotationProcessor
import com.mmolosay.stringannotations.processor.AnnotationProcessor

/*
 * Copyright 2022 Mikhail Malasai
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
 * Implementation of [BaseStyleAnnotationProcessor] for Compose UI.
 */
internal class StyleAnnotationProcessor : BaseStyleAnnotationProcessor<ComposeSpan>() {

    override val typefaceStyleAnnotationProcessor: AnnotationProcessor<ComposeSpan> =
        TypefaceStyleAnnotationProcessor()

    override fun makeUnderlineSpan(): ComposeSpan =
        SpanStyle(textDecoration = TextDecoration.Underline)

    override fun makeStrikethroughSpan(): ComposeSpan =
        SpanStyle(textDecoration = TextDecoration.LineThrough)
}