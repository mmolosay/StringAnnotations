package com.mmolosay.sample

import android.graphics.Color
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mmolosay.sample.databinding.MainScreenBinding
import com.mmolosay.stringannotations.getAnnotatedString
import com.mmolosay.stringannotations.spans.ClickableSpan

class MainActivity : AppCompatActivity(R.layout.main_screen) {

    private val binding by viewBinding(MainScreenBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setViews()
    }

    private fun setViews() {
        setDemos()
    }

    // region Set demos

    private fun setDemos() {
        setDemo1()
        setDemo2()
        setDemo3()
        setDemo4()
        setDemo5()
    }

    /**
     * Demo for [R.string.demo1].
     * Demonstrates foreground color annotation.
     */
    private fun setDemo1() {
        binding.textIndi1.text = getAnnotatedString(R.string.demo1)
    }

    /**
     * Demo for [R.string.demo2].
     * Demonstrates background color annotation.
     */
    private fun setDemo2() {
        val placeholder1 = getString(R.string.placeholder1)
        binding.textIndi2.text = getAnnotatedString(R.string.demo2, placeholder1)
    }

    /**
     * Demo for [R.string.demo3].
     * Demonstrates clickable annotations.
     *
     * Note: target `TextView` should be set with `setMovementMethod` in order to make clicks work.
     */
    private fun setDemo3() {
        val clickables = listOf(
            ClickableSpan(theme) {
                Toast.makeText(this, "Clicked text with index=0", Toast.LENGTH_SHORT).show()
            },
            ClickableSpan(
                theme = theme,
                builder = {
                    copy(
                        underlineText = true,
                        textColor = Color.MAGENTA
                    )
                }
            ) {
                Toast.makeText(this, "Clicked text with index=1", Toast.LENGTH_SHORT).show()
            }
        )
        binding.textIndi3.run {
            movementMethod = LinkMovementMethod.getInstance()
            text = getAnnotatedString(R.string.demo3, clickables)
        }
    }

    /**
     * Demo for [R.string.demo4].
     * Demonstrates typeface style annotations: normal, bold, italic and bold&italic.
     */
    private fun setDemo4() {
        binding.textIndi4.text = getAnnotatedString(R.string.demo4)
    }

    /**
     * Demo for [R.string.demo5].
     * Demonstrates crossed out and underlined style annotations.
     */
    private fun setDemo5() {
        binding.textIndi5.text = getAnnotatedString(R.string.demo5)
    }

    // endregion
}