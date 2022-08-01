package com.example.stringannotations

import android.graphics.Color
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.example.stringannotations.databinding.ActivityMainBinding
import com.example.stringannotations.lib.ClickableTextAppearance
import com.example.stringannotations.lib.StringAnnotations
import com.example.stringannotations.spans.ClickableSpan

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tests()
    }

    private fun tests() {
        test1()
        test2()
        test3()
        test4()
        binding.test4.movementMethod = LinkMovementMethod.getInstance()
        binding.test4.highlightColor = Color.TRANSPARENT
    }

    private fun test1() =
        testSpannedString(
            binding.test1,
            R.string.test1,
            emptyList(),
            "bold",
            "redddddddddd",
            "green"
        )

    private fun test2() =
        testSpannedString(
            binding.test2,
            R.string.test2
        )

    private fun test3() =
        testSpannedString(
            binding.test3,
            R.string.test3
        )

    private fun test4() =
        testSpannedString(
            binding.test4,
            R.string.test4,
            clickables = listOf(
                ClickableSpan {
                    Toast.makeText(this, "Index=0", Toast.LENGTH_SHORT).show()
                },
                ClickableSpan {
                    Toast.makeText(this, "Index=1", Toast.LENGTH_SHORT).show()
                },
                ClickableSpan {
                    Toast.makeText(this, "Index=2", Toast.LENGTH_SHORT).show()
                }
            )
        )

    private fun testSpannedString(
        targetView: TextView,
        @StringRes id: Int,
        clickables: List<ClickableSpan> = emptyList(),
        vararg formatArgs: Any
    ) {
        targetView.text = getAnnotatedString(id, clickables, *formatArgs)
    }
}