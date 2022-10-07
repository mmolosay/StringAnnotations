package com.mmolosay.sample.compose.custom

import com.mmolosay.stringannotations.compose.ComposeAnnotationValues
import com.mmolosay.stringannotations.compose.args.EmptyAnnotationValues

fun CustomArguments(
    values: ComposeAnnotationValues = EmptyAnnotationValues(),
    scope: CustomAnnotationValuesBuilder.() -> Unit
): CustomAnnotationValues =
    CustomAnnotationValuesBuilder(values).apply(scope).build()