package io.github.mmolosays.stringannotations

import android.text.Annotation
import android.text.SpannableString
import io.github.mmolosays.stringannotations.tree.AnnotationNode
import io.github.mmolosays.stringannotations.tree.AnnotationTreeBuilder
import io.kotest.matchers.shouldBe
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
internal class AnnotationTreeBuilderTests {

    /**
     *                            ___________
     * "a string with quite a few annotations"
     */
    @Test
    fun `buildAnnotationTrees #1`() {
        val annotations = arrayOf(
            Annotation("index", "0"),
        )
        val string = SpannableString("a string with quite a few annotations").apply {
            setSpan(annotations[0], 26, 37, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
        }

        val trees = AnnotationTreeBuilder.buildAnnotationTrees(string, annotations)

        val expected = listOf(
            AnnotationNode(
                annotation = annotations[0],
                children = emptyList(),
            )
        )

        trees shouldBe expected
    }

    /**
     *           ________________
     * "a string with quite a few annotations"
     */
    @Test
    fun `buildAnnotationTrees #2`() {
        val annotations = arrayOf(
            Annotation("index", "0"),
        )
        val string = SpannableString("a string with quite a few annotations").apply {
            setSpan(annotations[0], 9, 25, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
        }

        val trees = AnnotationTreeBuilder.buildAnnotationTrees(string, annotations)

        val expected = listOf(
            AnnotationNode(
                annotation = annotations[0],
                children = emptyList(),
            )
        )

        trees shouldBe expected
    }

    /**
     *           __________   _______________
     * "a string with quite a few annotations"
     */
    @Test
    fun `buildAnnotationTrees #3`() {
        val annotations = arrayOf(
            Annotation("index", "0"),
            Annotation("index", "1"),
        )
        val string = SpannableString("a string with quite a few annotations").apply {
            setSpan(annotations[0], 9, 19, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(annotations[1], 22, 37, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
        }

        val trees = AnnotationTreeBuilder.buildAnnotationTrees(string, annotations)

        val expected = listOf(
            AnnotationNode(
                annotation = annotations[0],
                children = emptyList(),
            ),
            AnnotationNode(
                annotation = annotations[1],
                children = emptyList(),
            ),
        )

        trees shouldBe expected
    }

    /**
     *           ____
     *           __________   ___
     * "a string with quite a few annotations"
     */
    @Test
    fun `buildAnnotationTrees #4`() {
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

        val trees = AnnotationTreeBuilder.buildAnnotationTrees(string, annotations)

        val expected = listOf(
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
        )

        trees shouldBe expected
    }

    /**
     *                _____
     *    ______ ___________        _________
     *    ___________________     ___________
     * "a string with quite a few annotations"
     */
    @Test
    fun `buildAnnotationTrees #5`() {
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

        val trees = AnnotationTreeBuilder.buildAnnotationTrees(string, annotations)

        val expected = listOf(
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
        )

        trees shouldBe expected
    }

    /**
     *                _____
     *  __------ ___________- ___ ___________
     * "a string with quite a few annotations"
     */
    @Test
    fun `buildAnnotationTrees #6`() {
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

        val trees = AnnotationTreeBuilder.buildAnnotationTrees(string, annotations)

        val expected = listOf(
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
        )

        trees shouldBe expected
    }

    /**
     *  _____________________________________
     * "a string with quite a few annotations"
     */
    @Test
    fun `buildAnnotationTrees #7`() {
        val annotations = arrayOf(
            Annotation("index", "0"),
        )
        val string = SpannableString("a string with quite a few annotations").apply {
            setSpan(annotations[0], 0, 37, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
        }

        val trees = AnnotationTreeBuilder.buildAnnotationTrees(string, annotations)

        val expected = listOf(
            AnnotationNode(
                annotation = annotations[0],
                children = emptyList(),
            ),
        )

        trees shouldBe expected
    }

    /**
     *                __
     *                _____   ___
     *           __________   ___
     * "a string with quite a few annotations"
     */
    @Test
    fun `buildAnnotationTrees #8`() {
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

        val trees = AnnotationTreeBuilder.buildAnnotationTrees(string, annotations)

        val expected = listOf(
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
        )

        trees shouldBe expected
    }

    /**
     *  ________------___________------------
     *  _____________________________________
     *  _____________________________________
     *  _____________________________________
     * "a string with quite a few annotations"
     */
    @Test
    fun `buildAnnotationTrees #9`() {
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

        val trees = AnnotationTreeBuilder.buildAnnotationTrees(string, annotations)

        val expected = listOf(
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
        )

        trees shouldBe expected
    }

    // endregion
}