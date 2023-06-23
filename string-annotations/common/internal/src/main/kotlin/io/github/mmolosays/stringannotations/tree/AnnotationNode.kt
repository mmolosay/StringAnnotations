package io.github.mmolosays.stringannotations.tree

import android.text.Annotation

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
 * [Annotation] that may contain other ones indside its range.
 * Represents a node in a tree-like structure.
 */
internal data class AnnotationNode(
    /**
     * The [Annotation] object instance itself.
     * If it's `null`, then this [AnnotationNode] is a top-most node holding the whole string.
     */
    val annotation: Annotation?,

    /**
     * Direct children of [annotation].
     */
    val children: List<AnnotationNode>,
)