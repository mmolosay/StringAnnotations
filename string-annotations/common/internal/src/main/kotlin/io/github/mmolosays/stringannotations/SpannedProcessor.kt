package io.github.mmolosays.stringannotations

import android.text.Annotation
import android.text.Spanned

/*
 * Copyright 2023 Mikhail Malasai
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
 * Processes [Spanned] objects.
 */
public object SpannedProcessor {

    /**
     * Retrieves spans of [Annotation] type from [string] in their appearance order (left to right).
     *
     * All `<annotation>` tags with more then one attribute will be split into multiple,
     * so that each has only one attribute.
     */
    public fun getAnnotationSpans(string: Spanned): Array<out Annotation> =
        string.getSpans(0, string.length, Annotation::class.java)
}