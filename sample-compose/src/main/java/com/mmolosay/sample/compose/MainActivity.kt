package com.mmolosay.sample.compose

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView()
    }

    private fun setContentView() {
        setContent {
            SampleTheme {
                MainScreen()
            }
        }
    }

    // TODO: delete
    // region Set demos

//    private fun setDemos() {
//        setDemo1()
//        setDemo2()
//        setDemo3()
//        setDemo4()
//        setDemo5()
//        setDemo6()
//        setDemo7()
//    }
//
//    /**
//     * Demo for [R.string.demo1].
//     * Demonstrates foreground color annotation.
//     */
//    private fun setDemo1() {
//        binding.demo1.text = getAnnotatedString(R.string.demo1)
//    }
//
//    /**
//     * Demo for [R.string.demo2].
//     * Demonstrates background color annotation.
//     */
//    private fun setDemo2() {
//        val placeholder1 = getString(R.string.placeholder1)
//        binding.demo2.text = getAnnotatedString(R.string.demo2, placeholder1)
//    }
//
//    /**
//     * Demo for [R.string.demo3].
//     * Demonstrates clickable annotations via runtime value arguments.
//     *
//     * Note: target `TextView` should be set with `setMovementMethod` in order to make clicks work.
//     */
//    private fun setDemo3() {
//        val appearance = ClickableSpan.Appearance.from(theme)
//        val span1 = ClickableSpan(appearance) {
//            Toast.makeText(this, "Clicked text with index=0", Toast.LENGTH_SHORT).show()
//        }
//        val span2 = ClickableSpan(
//            theme = theme,
//            builder = {
//                copy(
//                    underlineText = true,
//                    textColor = Color.MAGENTA
//                )
//            }
//        ) {
//            Toast.makeText(this, "Clicked text with index=1", Toast.LENGTH_SHORT).show()
//        }
//        val args = Arguments {
//            clickables {
//                add(span1)
//                add(span2)
//            }
//        }
//        binding.demo3.run {
//            movementMethod = LinkMovementMethod.getInstance()
//            text = getAnnotatedString(
//                id = R.string.demo3,
//                arguments = args
//            )
//        }
//    }
//
//    /**
//     * Demo for [R.string.demo4].
//     * Demonstrates typeface style annotations: normal, bold, italic and bold&italic.
//     */
//    private fun setDemo4() {
//        binding.demo4.text = getAnnotatedString(R.string.demo4)
//    }
//
//    /**
//     * Demo for [R.string.demo5].
//     * Demonstrates crossed out and underlined style annotations.
//     */
//    private fun setDemo5() {
//        binding.demo5.text = getAnnotatedString(R.string.demo5)
//    }
//
//    /**
//     * Demo for [R.string.demo6].
//     * Demonstrates crossed out and underlined style annotations.
//     */
//    private fun setDemo6() {
//        binding.demo6.text = getAnnotatedString(R.string.demo6)
//    }
//
//    /**
//     * Demo for [R.string.demo7].
//     * Demonstrates using runtime value arguments.
//     */
//    private fun setDemo7() {
//        val colorPurple = ContextCompat.getColor(this, R.color.purple_500)
//        val args = Arguments {
//            colors {
//                add(colorPurple)
//            }
//        }
//        binding.demo7.text = getAnnotatedString(R.string.demo7, args)
//    }

    // endregion
}