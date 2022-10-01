package com.mmolosay.stringannotations.common.shared.args

import com.mmolosay.stringannotations.common.shared.utils.Qualified

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
 * Collection of runtime values of some annotated string.
 *
 * `Arguments` is a great way to use values, which are tough (or completely impossible)
 * to parse from string, like click actions or dynamically computing values.
 */
public open class Arguments<E>(
    override val qualifier: String,
    private val list: List<E>
) : List<E> by list, Qualified {

    public constructor(qualifier: String, vararg args: E) : this(qualifier, args.asList())
}