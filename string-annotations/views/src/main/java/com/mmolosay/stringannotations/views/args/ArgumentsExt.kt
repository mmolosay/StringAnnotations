package com.mmolosay.stringannotations.views.args

import com.mmolosay.stringannotations.args.AnnotationValuesBuilder
import com.mmolosay.stringannotations.args.Arguments
import com.mmolosay.stringannotations.internal.args.AnnotationValuesBuilderImpl
import com.mmolosay.stringannotations.views.ViewsAnnotationValues
import com.mmolosay.stringannotations.views.ViewsArguments

/*
 * Copyright 2022 Mikhail Malasai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 * Extension for Arguments and stuff for convenience of use.
 */

/**
 * Assembles [Arguments] for Android Views UI in declarative style.
 */
public fun Arguments(
    builder: AnnotationValuesBuilder<Clickable>.() -> ViewsAnnotationValues
): ViewsArguments =
    ViewsArguments(builder(AnnotationValuesBuilderImpl()))