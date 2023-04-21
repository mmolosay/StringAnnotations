package io.github.mmolosays.stringannotations

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun SampleTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme() {
        content()
    }
}