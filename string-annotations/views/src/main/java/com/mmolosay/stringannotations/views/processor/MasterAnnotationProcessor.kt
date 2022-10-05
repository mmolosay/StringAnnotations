package com.mmolosay.stringannotations.views.processor

import com.mmolosay.stringannotations.processor.AbstractMasterAnnotationProcessor
import com.mmolosay.stringannotations.views.internal.ViewsAnnotationProcessor
import com.mmolosay.stringannotations.views.internal.ViewsArguments
import com.mmolosay.stringannotations.views.internal.ViewsSpan

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
 * Implementation of [AbstractMasterAnnotationProcessor] for Android Views system.
 */
public open class MasterAnnotationProcessor :
    AbstractMasterAnnotationProcessor<ViewsArguments, ViewsSpan>() {

    override fun createBackgroundColorAnnotationProcessor(): ViewsAnnotationProcessor =
        BackgroundColorAnnotationProcessor()

    override fun createForegroundColorAnnotationProcessor(): ViewsAnnotationProcessor =
        ForegroundColorAnnotationProcessor()

    override fun createStyleAnnotationProcessor(): ViewsAnnotationProcessor =
        StyleAnnotationProcessor()

    override fun createDecorationAnnotationProcessor(): ViewsAnnotationProcessor =
        DecorationAnnotationProcessor()

    override fun createClickableAnnotationProcessor(): ViewsAnnotationProcessor =
        ClickableAnnotationProcessor()

    override fun createSizeAnnotationProcessor(): ViewsAnnotationProcessor =
        SizeAnnotationProcessor()
}