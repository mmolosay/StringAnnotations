package io.github.mmolosays.stringannotations.views.processor

import android.text.style.AbsoluteSizeSpan
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StrikethroughSpan
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import io.github.mmolosays.stringannotations.BaseClickableAnnotationProcessor
import io.github.mmolosays.stringannotations.BaseColorAnnotationProcessor
import io.github.mmolosays.stringannotations.BaseDecorationAnnotationProcessor
import io.github.mmolosays.stringannotations.BaseSizeAnnotationProcessor
import io.github.mmolosays.stringannotations.BaseStyleAnnotationProcessor
import io.github.mmolosays.stringannotations.args.types.TextDecoration
import io.github.mmolosays.stringannotations.args.types.TextSize.SizeUnit
import io.github.mmolosays.stringannotations.parser.CommonValueParser
import io.github.mmolosays.stringannotations.parser.ValueParser
import io.github.mmolosays.stringannotations.processor.AbstractMasterAnnotationProcessor
import io.github.mmolosays.stringannotations.views.ViewsAnnotationProcessor
import io.github.mmolosays.stringannotations.views.ViewsArguments
import io.github.mmolosays.stringannotations.views.ViewsSpan
import io.github.mmolosays.stringannotations.views.clickable.CustomizableClickableSpan
import kotlin.math.roundToInt

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
public open class MasterAnnotationProcessor(
    override val defaultValueParser: ValueParser = CommonValueParser,
) :
    AbstractMasterAnnotationProcessor<ViewsArguments, ViewsSpan>() {

    override fun createBackgroundColorAnnotationProcessor(): ViewsAnnotationProcessor =
        BaseColorAnnotationProcessor(
            parser = defaultValueParser,
        ) {
            BackgroundColorSpan(it)
        }

    override fun createClickableAnnotationProcessor(): ViewsAnnotationProcessor =
        BaseClickableAnnotationProcessor(
            parser = defaultValueParser,
        ) {
            CustomizableClickableSpan(it)
        }

    override fun createForegroundColorAnnotationProcessor(): ViewsAnnotationProcessor =
        BaseColorAnnotationProcessor(
            parser = defaultValueParser,
        ) {
            ForegroundColorSpan(it)
        }

    override fun createDecorationAnnotationProcessor(): ViewsAnnotationProcessor =
        BaseDecorationAnnotationProcessor(
            parser = defaultValueParser,
        ) {
            when (it) {
                TextDecoration.Underline -> UnderlineSpan()
                TextDecoration.Striketrhough -> StrikethroughSpan()
                else -> null as ViewsSpan? // fails type infering without explicit cast
            }
        }

    override fun createSizeAnnotationProcessor(): ViewsAnnotationProcessor =
        BaseSizeAnnotationProcessor(
            parser = defaultValueParser,
        ) {
            when (it.unit) {
                SizeUnit.Px -> AbsoluteSizeSpan(it.value.roundToInt())
                SizeUnit.Sp -> throw IllegalArgumentException(
                    "AbsoluteSizeSpan does not support SP sizes. " +
                        "You can convert SP size in SizeUnit.Px on your own."
                )

                else -> null
            }
        }

    override fun createStyleAnnotationProcessor(): ViewsAnnotationProcessor =
        BaseStyleAnnotationProcessor(
            parser = defaultValueParser,
        ) {
            StyleSpan(it)
        }
}