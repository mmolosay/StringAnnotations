package com.example.stringannotations

import android.os.Bundle
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.example.stringannotations.databinding.ActivityMainBinding

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
    }

    private fun test1() =
        testSpannedString(
            binding.test1,
            R.string.test1,
            "bold",
            "redddddddddd",
            "green"
        )

    private fun testSpannedString(
        targetView: TextView,
        @StringRes id: Int,
        vararg formatArgs: Any
    ) {
        val spanned = AnnotatedStrings.format(this, id, *formatArgs)
        targetView.text = spanned
    }
}