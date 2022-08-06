package com.mmolosay.stringannotations

import android.text.Annotation

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
 * [Annotation] which has [start] and [end] positions in terms of some string.
 * It allows to work with annotation placement data without context of specific string.
 */
internal data class StringAnnotation(
    val annotation: Annotation,
    val start: Int,
    val end: Int,
    val index: Int
)

/**
 * Determines if [other] annotation is "inside" `this` one.
 * "Inside" means that it may be direct or inderect (nested) child.
 *
 * @return `true`, if [other] is inside, or `false`, if it's not or ([other] === `this`).
 */
internal fun StringAnnotation.has(other: StringAnnotation): Boolean {
    val a = (this !== other)            // is not the same exact object
    val b = (other.start >= this.start) // other is not starting earlier
    val c = (other.end <= this.end)     // other is not ending later
    return (a && b && c)
}