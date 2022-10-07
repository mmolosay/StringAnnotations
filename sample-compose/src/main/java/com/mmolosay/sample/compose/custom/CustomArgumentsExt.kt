package com.mmolosay.sample.compose.custom

import com.mmolosay.stringannotations.compose.ComposeAnnotationValues

fun Arguments(
    values: ComposeAnnotationValues,
    builder: CustomAnnotationValuesBuilder.() -> CustomAnnotationValues
): CustomAnnotationValues =
    builder(CustomAnnotationValuesBuilder((values)))