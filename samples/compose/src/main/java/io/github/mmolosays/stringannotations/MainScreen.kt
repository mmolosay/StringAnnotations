package io.github.mmolosays.stringannotations

import android.graphics.Typeface
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import io.github.mmolosays.stringannotations.args.types.TextDecoration
import io.github.mmolosays.stringannotations.args.types.TextSize
import io.github.mmolosays.stringannotations.args.types.TextSize.SizeUnit
import io.github.mmolosays.stringannotations.compose.annotatedStringResource
import io.github.mmolosays.stringannotations.compose.args.Arguments
import io.github.mmolosays.stringannotations.compose.args.Clickable
import io.github.mmolosays.stringannotations.compose.onClick
import io.github.mmolosays.stringannotations.sample.R

/*
 * Unfortunately, Compose Preview can not be rendered, when there's some
 * annotated strings in layout:
 * "class java.lang.String cannot be cast to class android.text.SpannedString",
 * but it's all fine in runtime
 */

@Composable
fun MainScreen() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(),
    ) {
        Main()
    }
}

@Composable
fun Main() {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Demo1()
        Demo2()
        Demo3()
        Demo4()
        Demo5()
        Demo6()
        CustomDemo()
    }
}

// region Demos

/**
 * Demo for [R.string.demo1].
 * Demonstrates foreground color annotation.
 */
@Composable
private fun Demo1() {
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
private fun Demo2() {
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
private fun Demo3() {
    val context = LocalContext.current
    val clickable1 = Clickable("first") {
        Toast
            .makeText(context, "Clicked annotation with index=0", Toast.LENGTH_SHORT)
            .show()
    }
    val clickable2 = Clickable("second") {
        Toast
            .makeText(context, "Clicked annotation with index=1", Toast.LENGTH_SHORT)
            .show()
    }
    val clickables = listOf(clickable1, clickable2)
    val args = Arguments {
        clickables(clickables)
    }
    val text = annotatedStringResource(R.string.demo3, args)
    ClickableText(
        text = text,
        onClick = { offset ->
            text.onClick(offset, clickables)
        },
        style = LocalTextStyle.current
    )
}

/**
 * Demo for [R.string.demo4].
 * Demonstrates typeface style annotations: bold, italic and bold&italic.
 */
@Composable
private fun Demo4() {
    val style1 = Typeface.ITALIC
    val style2 = Typeface.BOLD
    val style3 = Typeface.BOLD_ITALIC
    val args = Arguments {
        styles(style1, style2, style3)
    }
    Text(
        text = annotatedStringResource(R.string.demo4, args)
    )
}

/**
 * Demo for [R.string.demo5].
 * Demonstrates decoration annotations: `underline` and `strikethrough` as inline and using arguments.
 */
@Composable
private fun Demo5() {
    val args = Arguments {
        decoration(TextDecoration.Striketrhough)
        decoration(TextDecoration.Underline)
    }
    Text(
        text = annotatedStringResource(R.string.demo5, args)
    )
}

/**
 * Demo for [R.string.demo6].
 * Demonstrates absolute size annotation.
 */
@Composable
private fun Demo6() {
    val size = TextSize(10.5f, SizeUnit.Sp)
    val args = Arguments {
        size(size)
    }
    Text(
        text = annotatedStringResource(R.string.demo6, args)
    )
}

/**
 * Demo for [R.string.customDemo].
 * Demonstrates custom "letter-spacing" annotation type.
 */
@Composable
private fun CustomDemo() {
    val args = MyArguments(
        letterSpacings = listOf(TextUnit(10f, TextUnitType.Sp))
    )
    Text(text = annotatedStringResource(R.string.customDemo, args))
}

// endregion