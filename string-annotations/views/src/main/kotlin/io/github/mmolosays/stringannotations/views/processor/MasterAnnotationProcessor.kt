package io.github.mmolosays.stringannotations.views.processor

import io.github.mmolosays.stringannotations.processor.AbstractMasterAnnotationProcessor
import io.github.mmolosays.stringannotations.views.ViewsAnnotationProcessor
import io.github.mmolosays.stringannotations.views.ViewsArguments
import io.github.mmolosays.stringannotations.views.ViewsSpan

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
 * Implementation of [AbstractMasterAnnotationProcessor] for Android Views UI.
 *
 * One should inherit this class in order to extend out-of-the-box annotaiton types with
 * custom ones.
 */
public open class MasterAnnotationProcessor :
    AbstractMasterAnnotationProcessor<ViewsArguments, ViewsSpan>() {

    override fun createBackgroundColorAnnotationProcessor(): ViewsAnnotationProcessor =
        BackgroundColorAnnotationProcessor()

    override fun createClickableAnnotationProcessor(): ViewsAnnotationProcessor =
        ClickableAnnotationProcessor()

    override fun createForegroundColorAnnotationProcessor(): ViewsAnnotationProcessor =
        ForegroundColorAnnotationProcessor()

    override fun createDecorationAnnotationProcessor(): ViewsAnnotationProcessor =
        DecorationAnnotationProcessor()

    override fun createSizeAnnotationProcessor(): ViewsAnnotationProcessor =
        SizeAnnotationProcessor()

    override fun createStyleAnnotationProcessor(): ViewsAnnotationProcessor =
        StyleAnnotationProcessor()
}