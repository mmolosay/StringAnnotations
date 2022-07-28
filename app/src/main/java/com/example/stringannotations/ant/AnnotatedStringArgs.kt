package com.example.stringannotations.ant

object AnnotatedStringArgs {

    val test1StringArgs =
        arrayOf<AnnotationContent>(
            AnnotationContent.Basic("red")
        )

    val test2StringArgs =
        arrayOf<AnnotationContent>(
            AnnotationContent.Compound(
                AnnotationContent.Basic("red"),
                "bold"
            )
        )

}