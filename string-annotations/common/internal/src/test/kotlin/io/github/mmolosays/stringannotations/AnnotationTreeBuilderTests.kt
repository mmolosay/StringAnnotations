package io.github.mmolosays.stringannotations

import android.text.Annotation
import android.text.SpannableString
import io.github.mmolosays.stringannotations.tree.AnnotationNode
import io.github.mmolosays.stringannotations.tree.AnnotationTree
import io.github.mmolosays.stringannotations.tree.AnnotationTreeBuilder
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
internal class AnnotationTreeBuilderTests {

    /**
     *                            ___________
     * "a string with quite a few annotations"
     */
    @Test
    fun `buildAnnotationTree #1`() {
        val annotations = arrayOf(
            Annotation("index", "0"),
        )
        val string = SpannableString("a string with quite a few annotations").apply {
            setSpan(annotations[0], 26, 37, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
        }

        val tree = AnnotationTreeBuilder.buildAnnotationTree(string, annotations)

        val expected = AnnotationTree(
            children = listOf(
                AnnotationNode(
                    annotation = annotations[0],
                    children = emptyList(),
                ),
            ),
        )


        tree shouldBe expected
    }

    /**
     *           ________________
     * "a string with quite a few annotations"
     */
    @Test
    fun `buildAnnotationTree #2`() {
        val annotations = arrayOf(
            Annotation("index", "0"),
        )
        val string = SpannableString("a string with quite a few annotations").apply {
            setSpan(annotations[0], 9, 25, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
        }

        val tree = AnnotationTreeBuilder.buildAnnotationTree(string, annotations)

        val expected = AnnotationTree(
            children = listOf(
                AnnotationNode(
                    annotation = annotations[0],
                    children = emptyList(),
                ),
            ),
        )

        tree shouldBe expected
    }

    /**
     *           __________   _______________
     * "a string with quite a few annotations"
     */
    @Test
    fun `buildAnnotationTree #3`() {
        val annotations = arrayOf(
            Annotation("index", "0"),
            Annotation("index", "1"),
        )
        val string = SpannableString("a string with quite a few annotations").apply {
            setSpan(annotations[0], 9, 19, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(annotations[1], 22, 37, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
        }

        val tree = AnnotationTreeBuilder.buildAnnotationTree(string, annotations)

        val expected = AnnotationTree(
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

        tree shouldBe expected
    }

    /**
     *           ____
     *           __________   ___
     * "a string with quite a few annotations"
     */
    @Test
    fun `buildAnnotationTree #4`() {
        val annotations = arrayOf(
            Annotation("index", "0"),
            Annotation("index", "1"),
            Annotation("index", "2"),
        )
        val string = SpannableString("a string with quite a few annotations").apply {
            setSpan(annotations[0], 9, 19, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(annotations[1], 9, 13, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(annotations[2], 22, 25, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
        }

        val tree = AnnotationTreeBuilder.buildAnnotationTree(string, annotations)

        val expected = AnnotationTree(
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
                    children = emptyList(),
                ),
            ),
        )

        tree shouldBe expected
    }

    /**
     *                _____
     *    ______ ___________        _________
     *    ___________________     ___________
     * "a string with quite a few annotations"
     */
    @Test
    fun `buildAnnotationTree #5`() {
        val annotations = arrayOf(
            Annotation("index", "0"),
            Annotation("index", "1"),
            Annotation("index", "2"),
            Annotation("index", "3"),
            Annotation("index", "4"),
            Annotation("index", "5"),
        )
        val string = SpannableString("a string with quite a few annotations").apply {
            setSpan(annotations[0], 2, 21, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(annotations[1], 2, 8, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(annotations[2], 9, 20, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(annotations[3], 14, 19, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(annotations[4], 26, 37, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(annotations[5], 28, 37, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
        }

        val tree = AnnotationTreeBuilder.buildAnnotationTree(string, annotations)

        val expected = AnnotationTree(
            children = listOf(
                AnnotationNode(
                    annotation = annotations[0],
                    children = listOf(
                        AnnotationNode(
                            annotation = annotations[1],
                            children = emptyList(),
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
                ),
                AnnotationNode(
                    annotation = annotations[4],
                    children = listOf(
                        AnnotationNode(
                            annotation = annotations[5],
                            children = emptyList(),
                        ),
                    ),
                ),
            ),
        )

        tree shouldBe expected
    }

    /**
     *                _____
     *  __------ ___________- ___ ___________
     * "a string with quite a few annotations"
     */
    @Test
    fun `buildAnnotationTree #6`() {
        val annotations = arrayOf(
            Annotation("index", "0"),
            Annotation("index", "1"),
            Annotation("index", "2"),
            Annotation("index", "3"),
            Annotation("index", "4"),
            Annotation("index", "5"),
            Annotation("index", "6"),
        )
        val string = SpannableString("a string with quite a few annotations").apply {
            setSpan(annotations[0], 0, 2, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(annotations[1], 2, 8, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(annotations[2], 9, 20, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(annotations[3], 14, 19, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(annotations[4], 20, 21, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(annotations[5], 22, 25, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(annotations[6], 26, 37, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
        }

        val tree = AnnotationTreeBuilder.buildAnnotationTree(string, annotations)

        val expected = AnnotationTree(
            children = listOf(
                AnnotationNode(
                    annotation = annotations[0],
                    children = emptyList(),
                ),
                AnnotationNode(
                    annotation = annotations[1],
                    children = emptyList(),
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
                AnnotationNode(
                    annotation = annotations[4],
                    children = emptyList(),
                ),
                AnnotationNode(
                    annotation = annotations[5],
                    children = emptyList(),
                ),
                AnnotationNode(
                    annotation = annotations[6],
                    children = emptyList(),
                ),
            ),
        )

        tree shouldBe expected
    }

    /**
     *  _____________________________________
     * "a string with quite a few annotations"
     */
    @Test
    fun `buildAnnotationTree #7`() {
        val annotations = arrayOf(
            Annotation("index", "0"),
        )
        val string = SpannableString("a string with quite a few annotations").apply {
            setSpan(annotations[0], 0, 37, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
        }

        val tree = AnnotationTreeBuilder.buildAnnotationTree(string, annotations)

        val expected = AnnotationTree(
            children = listOf(
                AnnotationNode(
                    annotation = annotations[0],
                    children = emptyList(),
                )
            ),
        )

        tree shouldBe expected
    }

    /**
     *                __
     *                _____   ___
     *           __________   ___
     * "a string with quite a few annotations"
     */
    @Test
    fun `buildAnnotationTree #8`() {
        val annotations = arrayOf(
            Annotation("index", "0"),
            Annotation("index", "1"),
            Annotation("index", "2"),
            Annotation("index", "3"),
            Annotation("index", "4"),
        )
        val string = SpannableString("a string with quite a few annotations").apply {
            setSpan(annotations[0], 9, 19, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(annotations[1], 14, 19, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(annotations[2], 14, 16, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(annotations[3], 22, 25, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(annotations[4], 22, 25, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
        }

        val tree = AnnotationTreeBuilder.buildAnnotationTree(string, annotations)

        val expected = AnnotationTree(
            children = listOf(
                AnnotationNode(
                    annotation = annotations[0],
                    children = listOf(
                        AnnotationNode(
                            annotation = annotations[1],
                            children = listOf(
                                AnnotationNode(
                                    annotation = annotations[2],
                                    children = emptyList(),
                                ),
                            ),
                        ),
                    ),
                ),
                AnnotationNode(
                    annotation = annotations[3],
                    children = listOf(
                        AnnotationNode(
                            annotation = annotations[4],
                            children = emptyList(),
                        ),
                    ),
                ),
            ),
        )

        tree shouldBe expected
    }

    /**
     *  ________------___________------------
     *  _____________________________________
     *  _____________________________________
     *  _____________________________________
     * "a string with quite a few annotations"
     */
    @Test
    fun `buildAnnotationTree #9`() {
        val annotations = arrayOf(
            Annotation("index", "0"),
            Annotation("index", "1"),
            Annotation("index", "2"),
            Annotation("index", "3"),
            Annotation("index", "4"),
            Annotation("index", "5"),
            Annotation("index", "6"),
        )
        val string = SpannableString("a string with quite a few annotations").apply {
            setSpan(annotations[0], 0, 37, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(annotations[1], 0, 37, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(annotations[2], 0, 37, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(annotations[3], 0, 8, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(annotations[4], 8, 14, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(annotations[5], 14, 25, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(annotations[6], 25, 37, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
        }

        val tree = AnnotationTreeBuilder.buildAnnotationTree(string, annotations)

        val expected = AnnotationTree(
            children = listOf(
                AnnotationNode(
                    annotation = annotations[0],
                    children = listOf(
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
                                        AnnotationNode(
                                            annotation = annotations[5],
                                            children = emptyList(),
                                        ),
                                        AnnotationNode(
                                            annotation = annotations[6],
                                            children = emptyList(),
                                        ),
                                    ),
                                )
                            ),
                        )
                    ),
                ),
            ),
        )

        tree shouldBe expected
    }

    // endregion
}