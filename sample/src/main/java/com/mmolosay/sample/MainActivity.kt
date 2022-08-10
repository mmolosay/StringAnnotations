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
        setIndiDemos()
        setComplexDemos()
    }

    // region Set individual demos

    private fun setIndiDemos() {
        setIndiDemo1()
        setIndiDemo2()
        setIndiDemo3()
        setIndiDemo4()
        setIndiDemo5()
        setTmp()
    }

    private fun setIndiDemo1() {
        binding.textIndi1.text = getAnnotatedString(R.string.demoIndi1)
    }

    private fun setIndiDemo2() {
        val placeholder1 = getString(R.string.placeholder1)
        binding.textIndi2.text = getAnnotatedString(R.string.demoIndi2, placeholder1)
    }

    private fun setIndiDemo3() {
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
            text = getAnnotatedString(R.string.demoIndi3, clickables)
        }
    }

    private fun setIndiDemo4() {
        binding.textIndi4.text = getAnnotatedString(R.string.demoIndi4)
    }

    private fun setIndiDemo5() {
        binding.textIndi5.text = getAnnotatedString(R.string.demoIndi5)
    }

    private fun setTmp() {
        binding.tmp.text = getAnnotatedString(R.string.tmp)
    }

    // endregion

    // region Set complex demos

    private fun setComplexDemos() {

    }

    // endregion
}