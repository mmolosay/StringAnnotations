package com.mmolosay.sample.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// region Previews

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
    }
}

// region Demos

@Composable
private fun Demo1() {
    val text = stringResource(R.string.demo1)
    Text(text)
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