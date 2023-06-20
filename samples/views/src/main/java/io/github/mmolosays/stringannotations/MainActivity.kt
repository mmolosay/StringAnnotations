package io.github.mmolosays.stringannotations

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.util.TypedValue
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import io.github.mmolosays.stringannotations.args.types.TextSize
import io.github.mmolosays.stringannotations.args.types.TextSize.SizeUnit
import io.github.mmolosays.stringannotations.sample.R
import io.github.mmolosays.stringannotations.sample.databinding.MainScreenBinding
import io.github.mmolosays.stringannotations.views.args.Arguments
import io.github.mmolosays.stringannotations.views.args.Clickable
import io.github.mmolosays.stringannotations.views.clickable.Clickable
import io.github.mmolosays.stringannotations.views.clickable.from
import io.github.mmolosays.stringannotations.views.getAnnotatedString

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
        setDemo6()
    }

    /**
     * Demo for [R.string.demo1].
     * Demonstrates foreground color annotation.
     */
    private fun setDemo1() {
        val args = Arguments {
            color(Color.BLUE)
        }
        binding.demo1.text = getAnnotatedString(R.string.demo1, args)
    }

    /**
     * Demo for [R.string.demo2].
     * Demonstrates background color annotation.
     */
    private fun setDemo2() {
        val args = Arguments {
            color(Color.LTGRAY)
        }
        val placeholder1 = getString(R.string.value1)
        binding.demo2.text = getAnnotatedString(R.string.demo2, args, placeholder1)
    }

    /**
     * Demo for [R.string.demo3].
     * Demonstrates clickable annotations.
     *
     * Note: target `TextView` should be set with `setMovementMethod` in order to make clicks work.
     */
    private fun setDemo3() {
        val appearance = Clickable.Appearance.from(theme)
        val span1 = Clickable(appearance) {
            Toast.makeText(this, "Clicked text with index=0", Toast.LENGTH_SHORT).show()
        }
        val span2 = Clickable(
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
        val args = Arguments {
            clickables {
                add(span1)
                add(span2)
            }
        }
        binding.demo3.run {
            movementMethod = LinkMovementMethod.getInstance()
            text = getAnnotatedString(
                id = R.string.demo3,
                arguments = args
            )
        }
    }

    /**
     * Demo for [R.string.demo4].
     * Demonstrates typeface style annotations: bold, italic and bold&italic.
     */
    private fun setDemo4() {
        val style1 = Typeface.ITALIC
        val style2 = Typeface.BOLD
        val style3 = Typeface.BOLD_ITALIC
        val args = Arguments {
            styles(style1, style2, style3)
        }
        binding.demo4.text = getAnnotatedString(R.string.demo4, args)
    }

    /**
     * Demo for [R.string.demo5].
     * Demonstrates decoration annotations: underline and strikethrough.
     */
    private fun setDemo5() {
        binding.demo5.text = getAnnotatedString(R.string.demo5)
    }

    /**
     * Demo for [R.string.demo6].
     * Demonstrates absolute size annotations: in pixels, DIPs and SPs.
     */
    private fun setDemo6() {
        val value = 24.5f
        val metrics = resources.displayMetrics
        val size1 = TextSize(
            value = value,
            unit = SizeUnit.Px,
        )
        val size2 = TextSize(
            value = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, metrics),
            unit = SizeUnit.Px,
        )
        val size3 = TextSize(
            value = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, value, metrics),
            unit = SizeUnit.Px,
        )
        val args = Arguments {
            sizes(size1, size2, size3)
        }
        binding.demo6.text = getAnnotatedString(R.string.demo6, args)
    }

    // endregion
}