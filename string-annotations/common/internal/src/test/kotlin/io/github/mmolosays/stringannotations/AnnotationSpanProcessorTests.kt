package io.github.mmolosays.stringannotations

import android.text.Annotation
import android.text.SpannableString
import io.github.mmolosays.stringannotations.AnnotationSpanProcessor.rangeOf
import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.matchers.shouldBe
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

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

@RunWith(RobolectricTestRunner::class)
internal class AnnotationSpanProcessorTests {

    // region rangeOf

    @Test
    fun `rangeOf() returns valid range when annotation is attached`() {
        val expectedRange = 6..12
        val annotation = Annotation("key", "value")
        val string = SpannableString("first second third").apply {
            setSpan(
                annotation,
                expectedRange.first,
                expectedRange.last,
                SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE,
            )
        }

        val range = string rangeOf annotation

        range shouldBe expectedRange
    }

    @Test
    fun `rangeOf() throws exception when annotation is not attached`() {
        val annotation = Annotation("key", "value")
        val string = SpannableString("first second third")
        // setSpan is skipped, annotation is not attached

        shouldThrowAny {
            string rangeOf annotation
        }
    }

    // endregion
}