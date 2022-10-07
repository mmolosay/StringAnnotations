package com.mmolosay.sample.compose.custom

import com.mmolosay.stringannotations.args.Arguments
import com.mmolosay.stringannotations.args.values.AnnotationValues
import com.mmolosay.stringannotations.compose.args.EmptyAnnotationValues

/**
 * Assembles [Arguments] with [CustomAnnotationValues] in declarative style.
 */
fun CustomArguments(
    base: AnnotationValues = EmptyAnnotationValues(),
    scope: CustomAnnotationValuesBuilder.() -> Unit
): Arguments =
    Arguments(CustomAnnotationValues(base, scope))

/**
 * Assembles [CustomAnnotationValues] in declarative style.
 */
fun CustomAnnotationValues(
    base: AnnotationValues = EmptyAnnotationValues(),
    scope: CustomAnnotationValuesBuilder.() -> Unit
): CustomAnnotationValues =
    CustomAnnotationValuesBuilder().apply(scope).build(base)