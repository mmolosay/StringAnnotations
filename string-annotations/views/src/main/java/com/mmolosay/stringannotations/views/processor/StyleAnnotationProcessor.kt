package com.mmolosay.stringannotations.views.processor

import android.text.style.CharacterStyle
import android.text.style.StrikethroughSpan
import android.text.style.UnderlineSpan
import com.mmolosay.stringannotations.internal.processor.BaseStyleAnnotationProcessor

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
 * Implementation of [BaseStyleAnnotationProcessor] for Android Views system.
 */
internal class StyleAnnotationProcessor : BaseStyleAnnotationProcessor<CharacterStyle>() {

    override val typefaceStyleAnnotationProcessor = TypefaceStyleAnnotationProcessor()

    override fun makeUnderlineSpan(): CharacterStyle =
        UnderlineSpan()

    override fun makeStrikethroughSpan(): CharacterStyle =
        StrikethroughSpan()
}