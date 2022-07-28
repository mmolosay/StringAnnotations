package com.example.stringannotations

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.stringannotations.ant.AnnotatedStringArgs
import com.example.stringannotations.ant.StringAnnotationsUtils
import com.example.stringannotations.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        test()
    }

    private fun test() {
        test1()
        test2()
    }

    private fun test1() {
        val strres = R.string.test1
        val args = AnnotatedStringArgs.test1StringArgs
        val spanned = StringAnnotationsUtils.formatAnnotatedString(this, strres, *args)
        println() // TODO: remove
    }

    private fun test2() {
        val strres = R.string.test2
        val args = AnnotatedStringArgs.test2StringArgs
        val spanned = StringAnnotationsUtils.formatAnnotatedString(this, strres, *args)
        println() // TODO: remove
    }
}