package io.github.mmolosays.stringannotations.processor.parser

import io.github.mmolosays.stringannotations.args.qualified.QualifiedList

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
 * Specifies way of parsing placeholder into some actual value,
 * that will be used for span creation.
 */
public interface ValueParser {

    /**
     * Tries to parse [placeholder] as argument placeholder and obtain its actual value from [values].
     */
    public fun <V> parse(placeholder: String, values: QualifiedList<V>): V?
}