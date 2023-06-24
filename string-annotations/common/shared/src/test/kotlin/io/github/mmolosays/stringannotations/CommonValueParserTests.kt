package io.github.mmolosays.stringannotations

import android.graphics.Color
import io.github.mmolosays.stringannotations.args.qualified.QualifiedList
import io.github.mmolosays.stringannotations.parser.CommonValueParser
import io.github.mmolosays.stringannotations.parser.ValueParser
import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.matchers.shouldBe
import org.junit.Test

internal class CommonValueParserTests {

    private val parser: ValueParser = CommonValueParser

    @Test
    fun `parsing malformed placeholder throws exception #1`() {
        val placeholder = "gibberish"
        val values = QualifiedList("qualifier", listOf(1))

        shouldThrowAny {
            parser.parse(placeholder, values)
        }
    }

    @Test
    fun `parsing malformed placeholder throws exception #2`() {
        val qualifier = "color"
        val placeholder = "\$gibberish\$$qualifier\$0" // "$gibberish$color$0"
        val values = QualifiedList(qualifier, listOf(Color.WHITE))

        shouldThrowAny {
            parser.parse(placeholder, values)
        }
    }

    @Test
    fun `parsing malformed placeholder throws exception #3`() {
        val qualifier = "color"
        val placeholder = "\$arg\$gibberish\$0" // "$arg$gibberish$0"
        val values = QualifiedList(qualifier, listOf(Color.WHITE))

        shouldThrowAny {
            parser.parse(placeholder, values)
        }
    }

    @Test
    fun `parsing malformed placeholder throws exception #4`() {
        val qualifier = "color"
        val placeholder = "\$arg\$$qualifier\$gibberish" // "$arg$color$gibberish"
        val values = QualifiedList(qualifier, listOf(Color.WHITE))

        shouldThrowAny {
            parser.parse(placeholder, values)
        }
    }

    @Test
    fun `parsing valid placeholder with extra tokens throws exception`() {
        val qualifier = "color"
        val placeholder = "\$arg\$$qualifier\$0\$unexpected" // "$arg$color$0$unexpected"
        val values = QualifiedList(qualifier, listOf(Color.WHITE))

        shouldThrowAny {
            parser.parse(placeholder, values)
        }
    }

    @Test
    fun `parsing valid placeholder with index out of values bounds throws exception`() {
        val qualifier = "color"
        val placeholder = "\$arg\$$qualifier\$2" // "$arg$color$2", index=2 is a 3rd value
        val values = QualifiedList(qualifier, listOf(Color.WHITE, Color.BLACK))

        shouldThrowAny {
            parser.parse(placeholder, values)
        }
    }

    @Test
    fun `parsing valid placeholder with invalid values throws exception`() {
        val qualifier = "color"
        val placeholder = "\$arg\$$qualifier\$0" // "$arg$color$0"
        val values = QualifiedList("not color", listOf(1))

        shouldThrowAny {
            parser.parse(placeholder, values)
        }
    }

    @Test
    fun `parsing valid placeholder with valid values returns valid value`() {
        val qualifier = "color"
        val placeholder = "\$arg\$$qualifier\$0" // "$arg$color$0"
        val expected = Color.WHITE
        val values = QualifiedList(qualifier, listOf(expected))

        val value = parser.parse(placeholder, values)

        value shouldBe expected
    }

    @Test
    fun `parsing valid placeholder with more than necessary valid values returns valid value`() {
        val qualifier = "color"
        val placeholder = "\$arg\$$qualifier\$1" // "$arg$color$1"
        val expected = Color.WHITE
        val values = QualifiedList(qualifier, listOf(Color.BLACK, expected, Color.GREEN))

        val value = parser.parse(placeholder, values)

        value shouldBe expected
    }
}