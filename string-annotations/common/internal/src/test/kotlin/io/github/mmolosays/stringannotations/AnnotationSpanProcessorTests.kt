package io.github.mmolosays.stringannotations

import android.text.Annotation
import android.text.SpannableString
import io.github.mmolosays.stringannotations.AnnotationSpanProcessor.rangeOf
import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.matchers.shouldBe
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
internal class AnnotationSpanProcessorTests {

    // region parseAnnotationRange

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