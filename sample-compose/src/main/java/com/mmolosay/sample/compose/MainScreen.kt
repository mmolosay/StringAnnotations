package com.mmolosay.sample.compose

import android.graphics.Typeface
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.mmolosay.stringannotations.args.Arguments
import com.mmolosay.stringannotations.compose.annotatedStringResource
import com.mmolosay.stringannotations.compose.args.SpSize
import com.mmolosay.stringannotations.spans.clickable.ClickableSpan

// region Previews

/*
 * Unfortunately, Compose Preview can not be rendered, when there's some
 * annotated strings in layout:
 * class java.lang.String cannot be cast to class android.text.SpannedString,
 * but it all fine in runtime
 */
@Preview
@Composable
private fun MainScreenPreview() {
    SampleTheme {
        MainScreen()
    }
}

// endregion

@OptIn(ExperimentalUnitApi::class)
private val DemoTextStyle = TextStyle(fontSize = TextUnit(18f, TextUnitType.Sp))

@Composable
fun MainScreen() {
    Screen {
        Main()
    }
}

@Composable
fun Main() {
    CompositionLocalProvider(
        LocalTextStyle provides DemoTextStyle,
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Demo1()
            Demo2()
            Demo3()
            Demo4()
            Demo5()
            Demo6()
        }
    }
}

// region Demos

/**
 * Demo for [R.string.demo1].
 * Demonstrates foreground color annotation.
 */
@Composable
private fun Demo1() =
    Demo {
        val args = Arguments {
            color(Color.Blue.toArgb())
        }
        Text(
            text = annotatedStringResource(R.string.demo1, args)
        )
    }

/**
 * Demo for [R.string.demo2].
 * Demonstrates background color annotation.
 */
@Composable
private fun Demo2() =
    Demo {
        val args = Arguments {
            color(Color.LightGray.toArgb())
        }
        val formatValue1 = stringResource(R.string.value1)
        Text(
            text = annotatedStringResource(R.string.demo2, args, formatValue1)
        )
    }

/**
 * Demo for [R.string.demo3].
 * Demonstrates clickable annotations.
 */
@Composable
private fun Demo3() =
    Demo {
        val context = LocalContext.current
        val clickable1 = ClickableSpan {
            Toast
                .makeText(context, "Clicked annotation with index=0", Toast.LENGTH_SHORT)
                .show()
        }
        val appearance2 = ClickableSpan.Appearance(underlineText = true)
        val clickable2 = ClickableSpan(appearance2) {
            Toast
                .makeText(context, "Clicked annotation with index=1", Toast.LENGTH_SHORT)
                .show()
        }
        val args = Arguments {
            clickables(clickable1, clickable2)
        }
        Text(
            text = annotatedStringResource(R.string.demo3, args)
        )
    }

/**
 * Demo for [R.string.demo4].
 * Demonstrates typeface style annotations: bold, italic and bold&italic.
 */
@Composable
private fun Demo4() =
    Demo {
        val style1 = Typeface.ITALIC
        val style2 = Typeface.BOLD
        val style3 = Typeface.BOLD_ITALIC
        val args = Arguments {
            typefaceStyles(style1, style2, style3)
        }
        Text(
            text = annotatedStringResource(R.string.demo4, args)
        )
    }

/**
 * Demo for [R.string.demo5].
 * Demonstrates decoration annotations: underline and strikethrough.
 */
@Composable
private fun Demo5() =
    Demo {
        Text(
            text = annotatedStringResource(R.string.demo5)
        )
    }

/**
 * Demo for [R.string.demo6].
 * Demonstrates absolute size annotation.
 */
@Composable
private fun Demo6() =
    Demo {
        val size = SpSize(10.5f)
        val args = Arguments {
            absoluteSize(size)
        }
        Text(
            text = annotatedStringResource(R.string.demo6, args)
        )
    }

@Composable
private fun Demo(
    content: @Composable () -> Unit
) {
    Box(modifier = Modifier.padding(top = 8.dp)) {
        content()
    }
}

// endregion

@Composable
fun Screen(
    content: @Composable () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {
        content()
    }
}