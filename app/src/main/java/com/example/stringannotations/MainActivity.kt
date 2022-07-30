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
            FormattedStrings.test1,
            binding.test1
        )

    private fun testSpannedString(
        string: FormattedStringRes,
        targetView: TextView
    ) {
        val spanned = AnnotatedStrings.format(this, string.res, *string.args)
        targetView.text = spanned
    }
}