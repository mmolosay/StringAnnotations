package com.mmolosay.stringannotations.parser

import android.content.Context
import com.mmolosay.stringannotations.core.AnnotationTag

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

/**
 * Specifies way of parsing string annotation value into value of some appropriate [V] type.
 */
public interface AnnotationValueParser<V> {
    public fun parse(context: Context, value: AnnotationTag.Value): V?
}