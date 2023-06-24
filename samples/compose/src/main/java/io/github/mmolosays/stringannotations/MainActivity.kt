package io.github.mmolosays.stringannotations

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import io.github.mmolosays.stringannotations.compose.LocalStringAnnotationProcessor

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView()
    }

    private fun setContentView() {
        setContent {
            MaterialTheme {
                CompositionLocalProvider(
                    LocalStringAnnotationProcessor provides MyMasterAnnotationProcessor(),
                    LocalTextStyle provides TextStyle(fontSize = TextUnit(18f, TextUnitType.Sp)),
                ) {
                    MainScreen()
                }
            }
        }
    }
}