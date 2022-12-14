package com.mmolosay.stringannotations.compose.processor

import com.mmolosay.stringannotations.compose.ComposeAnnotationProcessor
import com.mmolosay.stringannotations.compose.ComposeArguments
import com.mmolosay.stringannotations.processor.AbstractMasterAnnotationProcessor

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
 * Implementation of [AbstractMasterAnnotationProcessor] for Compose UI.
 *
 * One should inherit this class in order to extend out-of-the-box annotaiton types with
 * custom ones.
 */
public open class MasterAnnotationProcessor :
    AbstractMasterAnnotationProcessor<ComposeArguments, ComposeSpan>() {

    override fun createBackgroundColorAnnotationProcessor(): ComposeAnnotationProcessor =
        BackgroundColorAnnotationProcessor()

    override fun createClickableAnnotationProcessor(): ComposeAnnotationProcessor =
        ClickableAnnotationProcessor()

    override fun createForegroundColorAnnotationProcessor(): ComposeAnnotationProcessor =
        ForegroundColorAnnotationProcessor()

    override fun createDecorationAnnotationProcessor(): ComposeAnnotationProcessor =
        DecorationAnnotationProcessor()

    override fun createSizeAnnotationProcessor(): ComposeAnnotationProcessor =
        SizeAnnotationProcessor()

    override fun createStyleAnnotationProcessor(): ComposeAnnotationProcessor =
        StyleAnnotationProcessor()
}