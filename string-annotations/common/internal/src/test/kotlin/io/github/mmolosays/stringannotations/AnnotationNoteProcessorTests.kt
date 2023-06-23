package io.github.mmolosays.stringannotations

import android.text.Annotation
import android.text.SpannableString
import io.github.mmolosays.stringannotations.tree.AnnotationNode
import io.github.mmolosays.stringannotations.tree.AnnotationNodeProcessor
import io.kotest.matchers.shouldBe
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
public class AnnotationNoteProcessorTests {

    /**
     *    ______
     * "a string with quite a few annotations"
     */
    @Test
    public fun `findNodeNonAnnotationRanges #1`() {
        val annotations = arrayOf(
            Annotation("index", "0"),
        )
        val string = SpannableString("a string with quite a few annotations").apply {
            setSpan(annotations[0], 2, 8, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        val node = AnnotationNode(
            annotation = annotations[0],
            children = emptyList(),
        )

        val ranges = AnnotationNodeProcessor.findNodeUnoccupiedRanges(node, string)

        val expected = listOf(
            2..8,
        )

        ranges shouldBe expected
    }

    /**
     *                            ___________
     * "a string with quite a few annotations"
     */
    @Test
    public fun `findNodeNonAnnotationRanges #2`() {
        val annotations = arrayOf(
            Annotation("index", "0"),
        )
        val string = SpannableString("a string with quite a few annotations").apply {
            setSpan(annotations[0], 26, 37, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        val node = AnnotationNode(
            annotation = annotations[0],
            children = emptyList(),
        )

        val ranges = AnnotationNodeProcessor.findNodeUnoccupiedRanges(node, string)

        val expected = listOf(
            26..37,
        )

        ranges shouldBe expected
    }

    /**
     *  _____________________________________
     * "a string with quite a few annotations"
     */
    @Test
    public fun `findNodeNonAnnotationRanges #3`() {
        val annotations = arrayOf(
            Annotation("index", "0"),
        )
        val string = SpannableString("a string with quite a few annotations").apply {
            setSpan(annotations[0], 0, 37, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        val node = AnnotationNode(
            annotation = annotations[0],
            children = emptyList(),
        )

        val ranges = AnnotationNodeProcessor.findNodeUnoccupiedRanges(node, string)

        val expected = listOf(
            0..37,
        )

        ranges shouldBe expected
    }

    /**
     *    ______
     *  ___________________
     * "a string with quite a few annotations"
     */
    @Test
    public fun `findNodeNonAnnotationRanges #4`() {
        val annotations = arrayOf(
            Annotation("index", "0"),
            Annotation("index", "1"),
        )
        val string = SpannableString("a string with quite a few annotations").apply {
            setSpan(annotations[0], 0, 19, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(annotations[1], 2, 8, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        val node = AnnotationNode(
            annotation = annotations[0],
            children = listOf(
                AnnotationNode(
                    annotation = annotations[1],
                    children = emptyList(),
                ),
            ),
        )

        val ranges = AnnotationNodeProcessor.findNodeUnoccupiedRanges(node, string)

        val expected = listOf(
            0..2,
            8..19,
        )

        ranges shouldBe expected
    }

    /**
     *  ________
     *  ___________________
     * "a string with quite a few annotations"
     */
    @Test
    public fun `findNodeNonAnnotationRanges #5`() {
        val annotations = arrayOf(
            Annotation("index", "0"),
            Annotation("index", "1"),
        )
        val string = SpannableString("a string with quite a few annotations").apply {
            setSpan(annotations[0], 0, 19, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(annotations[1], 0, 8, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        val node = AnnotationNode(
            annotation = annotations[0],
            children = listOf(
                AnnotationNode(
                    annotation = annotations[1],
                    children = emptyList(),
                ),
            ),
        )

        val ranges = AnnotationNodeProcessor.findNodeUnoccupiedRanges(node, string)

        val expected = listOf(
            8..19,
        )

        ranges shouldBe expected
    }

    /**
     *             ________
     *  ___________________
     * "a string with quite a few annotations"
     */
    @Test
    public fun `findNodeNonAnnotationRanges #6`() {
        val annotations = arrayOf(
            Annotation("index", "0"),
            Annotation("index", "1"),
        )
        val string = SpannableString("a string with quite a few annotations").apply {
            setSpan(annotations[0], 0, 19, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(annotations[1], 11, 19, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        val node = AnnotationNode(
            annotation = annotations[0],
            children = listOf(
                AnnotationNode(
                    annotation = annotations[1],
                    children = emptyList(),
                ),
            ),
        )

        val ranges = AnnotationNodeProcessor.findNodeUnoccupiedRanges(node, string)

        val expected = listOf(
            0..11,
        )

        ranges shouldBe expected
    }

    /**
     *    ______   ____
     *  ___________________
     * "a string with quite a few annotations"
     */
    @Test
    public fun `findNodeNonAnnotationRanges #7`() {
        val annotations = arrayOf(
            Annotation("index", "0"),
            Annotation("index", "1"),
            Annotation("index", "2"),
        )
        val string = SpannableString("a string with quite a few annotations").apply {
            setSpan(annotations[0], 0, 19, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(annotations[1], 2, 8, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(annotations[2], 11, 15, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        val node = AnnotationNode(
            annotation = annotations[0],
            children = listOf(
                AnnotationNode(
                    annotation = annotations[1],
                    children = emptyList(),
                ),
                AnnotationNode(
                    annotation = annotations[2],
                    children = emptyList(),
                ),
            ),
        )

        val ranges = AnnotationNodeProcessor.findNodeUnoccupiedRanges(node, string)

        val expected = listOf(
            0..2,
            8..11,
            15..19,
        )

        ranges shouldBe expected
    }

    /**
     *  ________   ____
     *  ___________________
     * "a string with quite a few annotations"
     */
    @Test
    public fun `findNodeNonAnnotationRanges #8`() {
        val annotations = arrayOf(
            Annotation("index", "0"),
            Annotation("index", "1"),
            Annotation("index", "2"),
        )
        val string = SpannableString("a string with quite a few annotations").apply {
            setSpan(annotations[0], 0, 19, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(annotations[1], 0, 8, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(annotations[2], 11, 15, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        val node = AnnotationNode(
            annotation = annotations[0],
            children = listOf(
                AnnotationNode(
                    annotation = annotations[1],
                    children = emptyList(),
                ),
                AnnotationNode(
                    annotation = annotations[2],
                    children = emptyList(),
                ),
            ),
        )

        val ranges = AnnotationNodeProcessor.findNodeUnoccupiedRanges(node, string)

        val expected = listOf(
            8..11,
            15..19,
        )

        ranges shouldBe expected
    }

    /**
     *    ______   ________
     *  ___________________
     * "a string with quite a few annotations"
     */
    @Test
    public fun `findNodeNonAnnotationRanges #9`() {
        val annotations = arrayOf(
            Annotation("index", "0"),
            Annotation("index", "1"),
            Annotation("index", "2"),
        )
        val string = SpannableString("a string with quite a few annotations").apply {
            setSpan(annotations[0], 0, 19, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(annotations[1], 2, 8, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(annotations[2], 11, 19, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        val node = AnnotationNode(
            annotation = annotations[0],
            children = listOf(
                AnnotationNode(
                    annotation = annotations[1],
                    children = emptyList(),
                ),
                AnnotationNode(
                    annotation = annotations[2],
                    children = emptyList(),
                ),
            ),
        )

        val ranges = AnnotationNodeProcessor.findNodeUnoccupiedRanges(node, string)

        val expected = listOf(
            0..2,
            8..11,
        )

        ranges shouldBe expected
    }

    /**
     *  ________   ________
     *  ___________________
     * "a string with quite a few annotations"
     */
    @Test
    public fun `findNodeNonAnnotationRanges #10`() {
        val annotations = arrayOf(
            Annotation("index", "0"),
            Annotation("index", "1"),
            Annotation("index", "2"),
        )
        val string = SpannableString("a string with quite a few annotations").apply {
            setSpan(annotations[0], 0, 19, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(annotations[1], 0, 8, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(annotations[2], 11, 19, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        val node = AnnotationNode(
            annotation = annotations[0],
            children = listOf(
                AnnotationNode(
                    annotation = annotations[1],
                    children = emptyList(),
                ),
                AnnotationNode(
                    annotation = annotations[2],
                    children = emptyList(),
                ),
            ),
        )

        val ranges = AnnotationNodeProcessor.findNodeUnoccupiedRanges(node, string)

        val expected = listOf(
            8..11,
        )

        ranges shouldBe expected
    }

    /**
     *  ___________________
     *  ___________________
     * "a string with quite a few annotations"
     */
    @Test
    public fun `findNodeNonAnnotationRanges #11`() {
        val annotations = arrayOf(
            Annotation("index", "0"),
            Annotation("index", "1"),
        )
        val string = SpannableString("a string with quite a few annotations").apply {
            setSpan(annotations[0], 0, 19, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(annotations[1], 0, 19, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        val node = AnnotationNode(
            annotation = annotations[0],
            children = listOf(
                AnnotationNode(
                    annotation = annotations[1],
                    children = emptyList(),
                ),
            ),
        )

        val ranges = AnnotationNodeProcessor.findNodeUnoccupiedRanges(node, string)

        ranges shouldBe emptyList()
    }

    /**
     *  ________———________
     *  ___________________
     * "a string with quite a few annotations"
     */
    @Test
    public fun `findNodeNonAnnotationRanges #12`() {
        val annotations = arrayOf(
            Annotation("index", "0"),
            Annotation("index", "1"),
            Annotation("index", "2"),
            Annotation("index", "3"),
        )
        val string = SpannableString("a string with quite a few annotations").apply {
            setSpan(annotations[0], 0, 19, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(annotations[1], 0, 8, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(annotations[2], 8, 11, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(annotations[3], 11, 19, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        val node = AnnotationNode(
            annotation = annotations[0],
            children = listOf(
                AnnotationNode(
                    annotation = annotations[1],
                    children = emptyList(),
                ),
                AnnotationNode(
                    annotation = annotations[2],
                    children = emptyList(),
                ),
                AnnotationNode(
                    annotation = annotations[3],
                    children = emptyList(),
                ),
            ),
        )

        val ranges = AnnotationNodeProcessor.findNodeUnoccupiedRanges(node, string)

        ranges shouldBe emptyList()
    }
}