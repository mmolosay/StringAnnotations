package com.mmolosay.stringannotations.args

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
 * Size of text, defined in some units.
 */
// can't be defined as `value` class, because will become not usable with `vararg`
public class TextSize(public val value: Float) {

    /**
     * Builder for [TextSize] in declarative style.
     */
    public constructor(computation: () -> Float) : this(computation())
}