package com.mmolosay.sample.compose

import android.graphics.Typeface
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mmolosay.stringannotations.args.Arguments
import com.mmolosay.stringannotations.compose.annotatedStringResource
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

@Composable
fun MainScreen() {
    Screen {
        Main()
    }
}

@Composable
fun Main() {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Demo1()
        Demo2()
        Demo3()
        Demo4()
        Demo5()
    }
}

// region Demos

@Composable
private fun Demo1() {
    val args = Arguments {
        color(Color.Blue.toArgb())
    }
    Text(
        text = annotatedStringResource(R.string.demo1, args)
    )
}

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

@Composable
private fun Demo3() {
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

@Composable
private fun Demo4() {
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

@Composable
private fun Demo5() {
    Text(
        text = annotatedStringResource(R.string.demo5)
    )
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