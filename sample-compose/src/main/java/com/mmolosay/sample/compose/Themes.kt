package com.mmolosay.sample.compose

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