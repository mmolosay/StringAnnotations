package com.example.stringannotations.ant

object AnnotatedStringArgs {

    val test1StringArgs =
        arrayOf(
            "red",
            "green",
            "blue"
        )

//    val test1StringArgs =
//        arrayOf<AnnotationContent>(
//            AnnotationContent.Basic("red")
//        )

    val test2StringArgs =
        arrayOf<AnnotationContent>(
            AnnotationContent.Compound(
                AnnotationContent.Basic("red"),
                "bold"
            )
        )

    val test4StringArgs =
        arrayOf<AnnotationContent>(
            AnnotationContent.Compound(
                AnnotationContent.Basic("1one"),
                "2two"
            )
        )
}