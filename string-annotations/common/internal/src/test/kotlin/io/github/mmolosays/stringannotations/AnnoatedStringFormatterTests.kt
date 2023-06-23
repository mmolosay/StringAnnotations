package io.github.mmolosays.stringannotations

import android.text.Annotation
import android.text.SpannableString
import io.github.mmolosays.stringannotations.tree.AnnotationNode
import io.github.mmolosays.stringannotations.tree.AnnotationTreeBuilder
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockkObject
import io.mockk.unmockkAll
import org.junit.After
import org.junit.Before
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
internal class AnnoatedStringFormatterTests {

    @Before
    fun beforeTest() {
        mockkObject(AnnotationTreeBuilder)
    }

    @After
    fun afterTest() {
        unmockkAll()
    }

    /**
     *            ____________
     * "here is a text without placeholders"
     */
    @Test
    fun `format #1`() {
        val formatArgs = emptyArray<String>()
        val annotations = arrayOf(
            Annotation("index", "0"),
        )
        val string = SpannableString("here is a text without placeholders").apply {
            setSpan(annotations[0], 10, 22, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        val tree = AnnotationNode(
            annotation = null,
            children = listOf(
                AnnotationNode(
                    annotation = annotations[0],
                    children = emptyList(),
                ),
            ),
        )

        every { AnnotationTreeBuilder.buildAnnotationTree(string, annotations) } returns tree

        val formatted = AnnotatedStringFormatter.format(string, annotations, *formatArgs)

        formatted shouldBe string // unchanged
    }

    /**
     *            ______________
     * "here is a placeholder #1: %1$s, #2: %2$s and text"
     */
    @Test
    fun `format #2`() {
        val formatArgs = arrayOf(
            "FIRST ARGUMENT",
            "SECOND ARGUMENT",
        )
        val annotations = arrayOf(
            Annotation("index", "0"),
        )
        val string = SpannableString("here is a placeholder #1: %1\$s, #2: %2\$s and text").apply {
            setSpan(annotations[0], 10, 24, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        val tree = AnnotationNode(
            annotation = null,
            children = listOf(
                AnnotationNode(
                    annotation = annotations[0],
                    children = emptyList(),
                ),
            ),
        )

        every { AnnotationTreeBuilder.buildAnnotationTree(string, annotations) } returns tree

        val formatted = AnnotatedStringFormatter.format(string, annotations, *formatArgs)

        val expected =
            SpannableString("here is a placeholder #1: ${formatArgs[0]}, #2: ${formatArgs[1]} and text").apply {
                setSpan(annotations[0], 10, 24, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
            }

        formatted shouldBe expected
    }

    /**
     *            ____________________
     * "here is a placeholder #1: %1$s, #2: %2$s and text"
     */
    @Test
    fun `format #3`() {
        val formatArgs = arrayOf(
            "FIRST ARGUMENT",
            "SECOND ARGUMENT",
        )
        val annotations = arrayOf(
            Annotation("index", "0"),
        )
        val string = SpannableString("here is a placeholder #1: %1\$s, #2: %2\$s and text").apply {
            setSpan(annotations[0], 10, 30, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        val tree = AnnotationNode(
            annotation = null,
            children = listOf(
                AnnotationNode(
                    annotation = annotations[0],
                    children = emptyList(),
                ),
            ),
        )

        every { AnnotationTreeBuilder.buildAnnotationTree(string, annotations) } returns tree

        val formatted = AnnotatedStringFormatter.format(string, annotations, *formatArgs)

        val expected =
            SpannableString("here is a placeholder #1: ${formatArgs[0]}, #2: ${formatArgs[1]} and text").apply {
                setSpan(annotations[0], 10, 40, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
            }

        formatted shouldBe expected
    }

    /**
     *            ____________________  ________
     * "here is a placeholder #1: %1$s, #2: %2$s and text"
     */
    @Test
    fun `format #4`() {
        val formatArgs = arrayOf(
            "FIRST ARGUMENT",
            "SECOND ARGUMENT",
        )
        val annotations = arrayOf(
            Annotation("index", "0"),
            Annotation("index", "1"),
        )
        val string = SpannableString("here is a placeholder #1: %1\$s, #2: %2\$s and text").apply {
            setSpan(annotations[0], 10, 30, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(annotations[1], 32, 40, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        val tree = AnnotationNode(
            annotation = null,
            children = listOf(
                AnnotationNode(
                    annotation = annotations[0],
                    children = emptyList(),
                ),
                AnnotationNode(
                    annotation = annotations[1],
                    children = emptyList(),
                ),
            ),
        )

        every { AnnotationTreeBuilder.buildAnnotationTree(string, annotations) } returns tree

        val formatted = AnnotatedStringFormatter.format(string, annotations, *formatArgs)

        val expected =
            SpannableString("here is a placeholder #1: ${formatArgs[0]}, #2: ${formatArgs[1]} and text").apply {
                setSpan(annotations[0], 10, 40, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
                setSpan(annotations[1], 42, 61, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
            }

        formatted shouldBe expected
    }

    /**
     *                        __            ____
     *            ____________________  ________
     * "here is a placeholder #1: %1$s, #2: %2$s and text"
     */
    @Test
    fun `format #5`() {
        val formatArgs = arrayOf(
            "FIRST ARGUMENT",
            "SECOND ARGUMENT",
        )
        val annotations = arrayOf(
            Annotation("index", "0"),
            Annotation("index", "1"),
            Annotation("index", "2"),
            Annotation("index", "3"),
        )
        val string = SpannableString("here is a placeholder #1: %1\$s, #2: %2\$s and text").apply {
            setSpan(annotations[0], 10, 30, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(annotations[1], 22, 24, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(annotations[2], 32, 40, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(annotations[3], 36, 40, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        val tree = AnnotationNode(
            annotation = null,
            children = listOf(
                AnnotationNode(
                    annotation = annotations[0],
                    children = listOf(
                        AnnotationNode(
                            annotation = annotations[1],
                            children = emptyList(),
                        ),
                    ),
                ),
                AnnotationNode(
                    annotation = annotations[2],
                    children = listOf(
                        AnnotationNode(
                            annotation = annotations[3],
                            children = emptyList(),
                        ),
                    ),
                ),
            ),
        )

        every { AnnotationTreeBuilder.buildAnnotationTree(string, annotations) } returns tree

        val formatted = AnnotatedStringFormatter.format(string, annotations, *formatArgs)

        val expected =
            SpannableString("here is a placeholder #1: ${formatArgs[0]}, #2: ${formatArgs[1]} and text").apply {
                setSpan(annotations[0], 10, 40, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
                setSpan(annotations[1], 22, 24, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
                setSpan(annotations[2], 42, 61, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
                setSpan(annotations[3], 46, 61, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
            }

        formatted shouldBe expected
    }

    /**
     *                        __  ____
     *                        ________      ____
     *  ____      ____________________  ________
     * "here is a placeholder #1: %1$s, #2: %2$s and text"
     */
    @Test
    fun `format #6`() {
        val formatArgs = arrayOf(
            "FIRST ARGUMENT",
            "SECOND ARGUMENT",
        )
        val annotations = arrayOf(
            Annotation("index", "0"),
            Annotation("index", "1"),
            Annotation("index", "2"),
            Annotation("index", "3"),
            Annotation("index", "4"),
            Annotation("index", "5"),
            Annotation("index", "6"),
        )
        val string = SpannableString("here is a placeholder #1: %1\$s, #2: %2\$s and text").apply {
            setSpan(annotations[0], 0, 4, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(annotations[1], 10, 30, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(annotations[2], 22, 30, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(annotations[3], 22, 24, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(annotations[4], 26, 30, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(annotations[5], 32, 40, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(annotations[6], 36, 40, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        val tree = AnnotationNode(
            annotation = null,
            children = listOf(
                AnnotationNode(
                    annotation = annotations[0],
                    children = emptyList(),
                ),
                AnnotationNode(
                    annotation = annotations[1],
                    children = listOf(
                        AnnotationNode(
                            annotation = annotations[2],
                            children = listOf(
                                AnnotationNode(
                                    annotation = annotations[3],
                                    children = emptyList(),
                                ),
                                AnnotationNode(
                                    annotation = annotations[4],
                                    children = emptyList(),
                                ),
                            ),
                        ),
                    ),
                ),
                AnnotationNode(
                    annotation = annotations[5],
                    children = listOf(
                        AnnotationNode(
                            annotation = annotations[6],
                            children = emptyList(),
                        ),
                    ),
                ),
            ),
        )

        every { AnnotationTreeBuilder.buildAnnotationTree(string, annotations) } returns tree

        val formatted = AnnotatedStringFormatter.format(string, annotations, *formatArgs)

        val expected =
            SpannableString("here is a placeholder #1: ${formatArgs[0]}, #2: ${formatArgs[1]} and text").apply {
                setSpan(annotations[0], 0, 4, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
                setSpan(annotations[1], 10, 40, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
                setSpan(annotations[2], 22, 40, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
                setSpan(annotations[3], 22, 24, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
                setSpan(annotations[4], 26, 40, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
                setSpan(annotations[5], 42, 61, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
                setSpan(annotations[6], 46, 61, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
            }

        formatted shouldBe expected
    }
}