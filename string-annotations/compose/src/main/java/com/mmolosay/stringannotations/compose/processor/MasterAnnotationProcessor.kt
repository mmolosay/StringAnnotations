package com.mmolosay.stringannotations.compose.processor

import com.mmolosay.stringannotations.compose.internal.ComposeAnnotationProcessor
import com.mmolosay.stringannotations.compose.internal.ComposeSpan
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
 */
public class MasterAnnotationProcessor : AbstractMasterAnnotationProcessor<ComposeSpan>() {

    override fun createBackgroundColorAnnotationProcessor(): ComposeAnnotationProcessor =
        BackgroundColorAnnotationProcessor()

    override fun createForegroundColorAnnotationProcessor(): ComposeAnnotationProcessor =
        ForegroundColorAnnotationProcessor()

    override fun createStyleAnnotationProcessor(): ComposeAnnotationProcessor =
        TODO() // StyleAnnotationProcessor()

    override fun createClickableAnnotationProcessor(): ComposeAnnotationProcessor =
        TODO() // ClickableAnnotationProcessor()

    override fun createAbsoluteSizeAnnotationProcessor(): ComposeAnnotationProcessor =
        TODO() // AbsoluteSizeAnnotationProcessor()
}