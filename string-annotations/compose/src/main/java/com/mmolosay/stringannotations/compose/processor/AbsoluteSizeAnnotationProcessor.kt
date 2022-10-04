package com.mmolosay.stringannotations.compose.processor

import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import com.mmolosay.stringannotations.compose.internal.ComposeSpan
import com.mmolosay.stringannotations.internal.processor.AbsoluteSizeAnnotationProcessor

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
 * Implementation of [AbsoluteSizeAnnotationProcessor] for Compose UI.
 */
internal class AbsoluteSizeAnnotationProcessor : AbsoluteSizeAnnotationProcessor<ComposeSpan>() {

    @OptIn(ExperimentalUnitApi::class)
    override fun makeSpan(value: Int): ComposeSpan =
        SpanStyle(
            fontSize = TextUnit(value.toFloat(), TextUnitType.Sp)
        )
}